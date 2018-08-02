package com.one.rpcwithzookeeper.zk;

/**
 * Created by huangyifei on 2018/7/31.
 */
public interface IServiceDiscovery {
    /**
     * 通过serviceName获取可用的访问地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
