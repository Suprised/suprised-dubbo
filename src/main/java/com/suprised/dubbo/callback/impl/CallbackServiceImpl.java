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
                                listeners.remove(entry.getKey());// �ͻ��˶Ͽ����ӻ�����쳣��Ȼ�󽫻ص��Ƴ���
                            }
                        }
                        Thread.sleep(5000); // ��ʱ�������֪ͨ
                    } catch (Throwable t) { // �����ݴ�
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
