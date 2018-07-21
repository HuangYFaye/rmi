package com.one;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class Server {
    public static void main(String[] args) {
        try {
            IHelloService helloService = new HelloServiceImpl();
            LocateRegistry.createRegistry(1099);

            Naming.bind("rmi://127.0.0.1/Hello",helloService);
            System.out.println("服务启动成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
