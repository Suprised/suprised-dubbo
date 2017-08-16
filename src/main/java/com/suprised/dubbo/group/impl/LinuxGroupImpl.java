package com.suprised.dubbo.group.impl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.group.LimitException;
import com.suprised.dubbo.group.ServerGroup;

@Service(version = "1.0.0", group = "linux")
public class LinuxGroupImpl implements ServerGroup {

    // �����ɴ�С����dubboЭ����̳߳��̴߳�С����
    private static Semaphore semaphore = new Semaphore(20, false);// �ǹ�ƽ��ȡ���

    // Ҳ����ʹ��ԭ�Ӹ��²���ʵ��AtomicLong
    private static AtomicLong atomicLong = new AtomicLong(0);
    
    @Override
    public String operateSystemCommad() {
        return "linux";
    }

    @Override
    public String limitAccess()  throws LimitException {
        // System.out.println(Thread.currentThread().getName() + "-linux���õ���ɣ�" + semaphore.drainPermits());
        try {
            semaphore.acquire();// ��ȡ��� ����δ��ȡ����ɵ��߳�
            atomicLong.incrementAndGet();
            Thread.sleep(150);// ִ������
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();// �ͷ����
            atomicLong.decrementAndGet();
        }
        return "linuxִ��������   " + atomicLong.get();
    }
    
    public static void main(String[] args) throws Exception {
        final LinuxGroupImpl impl = new LinuxGroupImpl();
        for (int i=0; i<50; i++) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    System.out.println(impl.limitAccess());
                }
            }).start();
        }
        
        Thread.sleep(10000);
    }

}
