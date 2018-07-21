package com.one.rpc;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class HelloImpl implements IHello
{
    @Override
    public String sayHello(String msg) {
        return "Hello ," + msg;
    }
}
