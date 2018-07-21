package com.one.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class RpcServer {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port){


        ServerSocket serverSocket = null;
        try {
            serverSocket= new ServerSocket(port);

            while (true) {
                System.out.println("准备监听");
                Socket socket = serverSocket.accept();
                System.out.println("监听ING");
                executorService.execute(new ProcessorHandler(socket,service));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
