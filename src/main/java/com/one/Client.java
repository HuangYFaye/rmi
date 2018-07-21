package com.one;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by huangyifei on 2018/7/21.
 */
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IHelloService helloService = (IHelloService)Naming.lookup("rmi://127.0.0.1/Hello");
        System.out.println(helloService.Hello("One"));
    }
}
