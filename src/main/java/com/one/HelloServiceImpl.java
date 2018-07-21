package com.one;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String Hello(String msg) throws RemoteException {
        return "Hello," + msg;
    }
}
