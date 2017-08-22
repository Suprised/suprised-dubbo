package com.suprised.dubbo.event.impl;

import com.suprised.dubbo.event.ServiceEvent;

public class ServiceEventImpl implements ServiceEvent {

    @Override
    public void oninvoke(String param) {
        System.out.println("server��oninvokey(); param=" + param);
    }

    @Override
    public void onreturn(String result, String param) {
        System.out.println("server��onreturn(); result:" + result + ", param:" + param);
    }

    @Override
    public void onthrow(Throwable e, String param) {
        System.out.println("server��onthrow(); param:" + param);
        e.printStackTrace();
    }

}
