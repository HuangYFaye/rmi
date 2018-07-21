package com.one;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by huangyifei on 2018/7/20.
 */
public interface IHelloService extends Remote {
    String Hello(String msg) throws RemoteException;
}
