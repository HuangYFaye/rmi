package com.one.rpcwithzookeeper;
import com.one.rpcwithzookeeper.anno.zkService;

/**
 * Created by huangyifei on 2018/7/20.
 */

@zkService(IHello.class)
public class HelloImpl implements IHello
{
    @Override
    public String sayHello(String msg) {
        return "Hello ," + msg;
    }
}
