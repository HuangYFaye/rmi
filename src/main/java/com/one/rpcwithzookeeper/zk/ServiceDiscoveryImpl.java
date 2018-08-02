package com.one.rpcwithzookeeper.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyifei on 2018/7/31.
 */
public class ServiceDiscoveryImpl implements IServiceDiscovery {
    private CuratorFramework curatorFramework;

    List<String>  repos = new ArrayList<>();

    String address;

    public ServiceDiscoveryImpl(String address) {
        this.address = address;
        //连接Zookeeper
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(address).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).
                build();

        curatorFramework.start();
    }

    @Override
    public String discover(String serviceName) {
        String path = zkConfig.RIGISTER_PATH + "/" + serviceName;
        try {
            repos = curatorFramework.getChildren().forPath(path);
            RandomLoadBalance randomLoadBalance = new RandomLoadBalance();
            //负载均衡
            return randomLoadBalance.selectHost(repos);

        } catch (Exception e) {
            new RuntimeException("获取"+path+"下的子节点失败",e);
        }
        return null;
    }
}
