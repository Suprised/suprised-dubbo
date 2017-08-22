package com.suprised.dubbo.event.impl;

import com.suprised.dubbo.event.ServiceEvent;

public class ServiceEventImpl implements ServiceEvent {

    @Override
    public void oninvoke(String param) {
        System.out.println("server£ºoninvokey(); param=" + param);
    }

    @Override
    public void onreturn(String result, String param) {
        System.out.println("server£ºonreturn(); result:" + result + ", param:" + param);
    }

    @Override
    public void onthrow(Throwable e, String param) {
        System.out.println("server£ºonthrow(); param:" + param);
        e.printStackTrace();
    }

}
