package com.one.rpcwithzookeeper.zk;

import java.util.List;
import java.util.Random;

/**
 * Created by huangyifei on 2018/7/31.
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> repos) {
        int len = repos.size();
        Random random = new Random();
        return repos.get(random.nextInt(len));
    }
}
