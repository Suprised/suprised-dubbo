package com.suprised.dubbo.mock.impl;

import com.alibaba.dubbo.rpc.RpcException;
import com.suprised.dubbo.mock.MockService;

public class MockServiceImpl implements MockService {

    @Override
    public String invocMock(String param) {
        return "mock result:" + param;
    }

    @Override
    public String invocMockThrowException(String param) {
        throw new RpcException("rpcException: " + param);
    }

}
