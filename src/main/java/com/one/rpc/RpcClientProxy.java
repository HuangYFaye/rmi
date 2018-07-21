package com.one.rpc;

import java.lang.reflect.Proxy;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class RpcClientProxy {

    public  <T> T clientProxy(final Class<T> interfaceCls, final String host,int port){
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[] {interfaceCls},
                new RemoteInvocationHandler(host,port));
    }
}
