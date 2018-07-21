package com.one.rpc;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();

        //JDK动态代理，因为客户端根本没有IHello的实现类
        IHello hello = rpcClientProxy.clientProxy(IHello.class,"localhost",8888);
        //代理类hello实际调用了invoke方法
        System.out.println(hello.sayHello("One"));
    }
}
