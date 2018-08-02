package com.one.rpcwithzookeeper.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * Created by huangyifei on 2018/7/30.
 */
public class RigisterCenterImpl implements IRigisterCenter {

    private CuratorFramework curatorFramework;

    {
        //连接Zookeeper
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(zkConfig.ZOOKEEPER_ADDRESS).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).
                build();

        curatorFramework.start();
    }

    @Override
    public void Rigister(String ServiceName, String serviceAddress) {
        //注册服务
        String servicePath = zkConfig.RIGISTER_PATH + "/" + ServiceName;

        try {
            //若判断 /registrys/product-service 是否存在，不存在则创建
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).
                        forPath(servicePath,"0".getBytes());
            }

            String addressPath = servicePath +"/"+serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功" + rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
