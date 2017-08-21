package com.suprised.dubbo.async.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.async.AsyncInvocationService;

@Service(version = "1.0.0")
public class AsyncInvocationServiceImpl implements AsyncInvocationService {

    @Override
    public String asyncInvocation(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "异步调用，调用时直接返回空，使用Future.get()返回结果。";
    }

    @Override
    public String syncInvocation(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "同步调用，客户端等待执行结果返回。";
    }

}
