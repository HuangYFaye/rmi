package com.one.rpcwithzookeeper;

import com.one.rpcwithzookeeper.zk.IRigisterCenter;
import com.one.rpcwithzookeeper.zk.RigisterCenterImpl;

/**
 * Created by huangyifei on 2018/7/21.
 */
public class ServerDemo2 {
    public static void main(String[] args) {
        IHello Hello= new HelloImpl();
        IRigisterCenter rigisterCenter = new RigisterCenterImpl();

        RpcServer rpcServer = new RpcServer(rigisterCenter,"127.0.0.1:8081");
        rpcServer.bind(Hello);
        rpcServer.publisher();
    }
}
