package com.one.rpc;

/**
 * Created by huangyifei on 2018/7/21.
 */
public class ServerDemo {
    public static void main(String[] args) {
        IHello Hello= new HelloImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(Hello,8888);
    }
}
