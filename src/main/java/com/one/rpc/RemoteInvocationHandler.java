package com.one.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过代理，拦截被调用的方法，参数，并组装成RpcRequest类，通过TCP协议发送到服务端
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        TCPTransport tcpTransport = new TCPTransport(this.host,this.port);

        return tcpTransport.send(rpcRequest);
    }
}
