package com.suprised.dubbo.stub.impl;

import com.suprised.dubbo.stub.StubService;

public class StubServiceImpl implements StubService {

    @Override
    public String invoca(String param) {
        return "result: " + param;
    }

}
