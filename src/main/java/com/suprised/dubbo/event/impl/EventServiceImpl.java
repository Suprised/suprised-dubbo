package com.suprised.dubbo.event.impl;

import com.suprised.dubbo.event.EventService;

public class EventServiceImpl implements EventService {

    @Override
    public String invoca(String param) {
        System.out.println("ִ�з��񷽷���invocation();");
        return "invocation()";
    }

    @Override
    public String invocaThrowException(String param) {
        System.out.println("ִ�з��񷽷����׳��쳣��invocationThrowException();");
        throw new RuntimeException("invocationThrowException()");
    }
}
