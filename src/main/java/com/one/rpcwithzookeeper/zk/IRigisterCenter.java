package com.one.rpcwithzookeeper.zk;

/**
 * Created by huangyifei on 2018/7/30.
 */
public interface IRigisterCenter {

    /**
     * 注册服务名称与地址
     * @param ServiceName
     * @param serviceAddress
     */
    void Rigister(String ServiceName,String serviceAddress);
}



