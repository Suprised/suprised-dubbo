package com.suprised.dubbo.event.impl;

import com.suprised.dubbo.event.EventService;

public class EventServiceImpl implements EventService {

    @Override
    public String invoca(String param) {
        System.out.println("执行服务方法：invocation();");
        return "invocation()";
    }

    @Override
    public String invocaThrowException(String param) {
        System.out.println("执行服务方法并抛出异常：invocationThrowException();");
        throw new RuntimeException("invocationThrowException()");
    }
}
