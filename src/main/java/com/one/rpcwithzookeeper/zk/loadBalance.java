package com.one.rpcwithzookeeper.zk;

import java.util.List;

/**
 * Created by huangyifei on 2018/7/31.
 */
public interface loadBalance {
    String selectHost(List<String> repos);
}
