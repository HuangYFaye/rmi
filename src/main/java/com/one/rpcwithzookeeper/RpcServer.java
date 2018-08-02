package com.one.rpcwithzookeeper;

import com.one.rpcwithzookeeper.zk.IRigisterCenter;
import com.one.rpcwithzookeeper.anno.zkService;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangyifei on 2018/7/20.
 */
public class RpcServer {

    //线程池
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private IRigisterCenter RigisterCenter;//服务中心
    private String serviceAddress;//发布地址

    public RpcServer(IRigisterCenter RigisterCenter, String serviceAddress) {
        this.RigisterCenter = RigisterCenter;
        this.serviceAddress = serviceAddress;
    }

    //服务名称对应的服务对象容器
    Map<String,Object> handlerMap = new HashMap<>();

    /**
     * 绑定服务到容器
     * @param services
     */
    public void bind(Object...services){
        for (Object service : services) {
            zkService annotation = service.getClass().getAnnotation(zkService.class);
            String serviceName = annotation.value().getName();
            handlerMap.put(serviceName,service);
            RigisterCenter.Rigister(serviceName,serviceAddress);
        }
    }

    /**
     *
     */
    public void publisher(){
        ServerSocket serverSocket = null;

        try {
        String[] address = serviceAddress.split(":");
        int port = Integer.parseInt(address[1]);

            serverSocket= new ServerSocket(port);
            while (true) {
                System.out.println("准备监听");
                Socket socket = serverSocket.accept();
                System.out.println("监听ING");
                executorService.execute(new ProcessorHandler(socket,handlerMap));
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
