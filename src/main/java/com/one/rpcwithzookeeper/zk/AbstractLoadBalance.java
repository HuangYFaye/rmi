package com.one.rpcwithzookeeper.zk;

import java.util.List;

/**
 * Created by huangyifei on 2018/7/31.
 */
public abstract class AbstractLoadBalance implements loadBalance {


    @Override
    public String selectHost(List<String> repos) {
        if (repos == null){
            return null;
        }
        if (repos.size() == 1){
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);

}
