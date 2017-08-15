package com.suprised.dubbo.helloworld.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.helloworld.HelloworldAnnotationService;

@Service(cluster="failover", retries=2, version = "1.0.0")
public class HelloworldAnnotationServiceImpl implements HelloworldAnnotationService {

    public long calcAdd(int i, int j) {
        return (i + j);
    }

    public long calcDel(int i, int j) {
        return (i - j);
    }

}
