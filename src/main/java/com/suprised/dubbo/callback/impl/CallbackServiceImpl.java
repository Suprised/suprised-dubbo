package com.suprised.dubbo.callback.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.suprised.dubbo.callback.CallbackListener;
import com.suprised.dubbo.callback.CallbackService;

public class CallbackServiceImpl implements CallbackService {

    private final ConcurrentHashMap<String, CallbackListener> listeners = new ConcurrentHashMap<String, CallbackListener>();

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        listener.changed(getChanged(key));
        registerListener();
    }
    
    private void registerListener() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        for (Map.Entry<String, CallbackListener> entry : listeners.entrySet()) {
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                t.printStackTrace();
                                listeners.remove(entry.getKey());// 客户端断开连接会出现异常，然后将回调移除。
                            }
                        }
                        Thread.sleep(5000); // 定时触发变更通知
                    } catch (Throwable t) { // 防御容错
                        t.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
