package com.one.rpcwithzookeeper;

import com.one.rpcwithzookeeper.zk.IServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class RpcClientProxy {

    public  <T> T clientProxy(final Class<T> interfaceCls, IServiceDiscovery serviceDiscovery){
        //返回一个实现了interfaceCls接口的一个实体类
        //newProxyInstance（ClassLoader,interface数组，调用处理器）
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[] {interfaceCls},
                new RemoteInvocationHandler(serviceDiscovery));
    }
}
