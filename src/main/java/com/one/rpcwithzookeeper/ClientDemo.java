package com.one.rpcwithzookeeper;

import com.one.rpcwithzookeeper.zk.IServiceDiscovery;
import com.one.rpcwithzookeeper.zk.ServiceDiscoveryImpl;
import com.one.rpcwithzookeeper.zk.zkConfig;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();

        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl(zkConfig.ZOOKEEPER_ADDRESS);

        //JDK动态代理，因为客户端根本没有IHello的实现类
        IHello hello = rpcClientProxy.clientProxy(IHello.class,serviceDiscovery);;
        //代理类hello实际调用了invoke方法
        for (int i = 1; i < 10; i++) {
            System.out.println(hello.sayHello("One"));
        }
    }
}
