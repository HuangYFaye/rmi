package com.one.rpcwithzookeeper;

import com.one.rpcwithzookeeper.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class RemoteInvocationHandler implements InvocationHandler {

    IServiceDiscovery serviceDiscovery;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过代理，拦截被调用的方法，参数，并组装成RpcRequest类，通过TCP协议发送到服务端
        String address = serviceDiscovery.discover(method.getDeclaringClass().getName());
        String host = address.split(":")[0];
        String port = address.split(":")[1];
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        TCPTransport tcpTransport = new TCPTransport(host,Integer.parseInt(port));

        return tcpTransport.send(rpcRequest);
    }
}
