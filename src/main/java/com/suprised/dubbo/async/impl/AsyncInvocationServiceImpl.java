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
        return "�첽���ã�����ʱֱ�ӷ��ؿգ�ʹ��Future.get()���ؽ����";
    }

    @Override
    public String syncInvocation(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ͬ�����ã��ͻ��˵ȴ�ִ�н�����ء�";
    }

}
