package com.suprised.dubbo.group.impl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.group.LimitException;
import com.suprised.dubbo.group.ServerGroup;

@Service(version="1.0.0", group="windows")
public class WindowsGoupImpl implements ServerGroup {

    private static Semaphore semaphore = new Semaphore(10,false);// �ǹ�ƽ��ȡ���
    // Ҳ����ʹ��ԭ�Ӹ��²���ʵ��AtomicLong
    
    private static AtomicLong atomicLong = new AtomicLong(0);
    
    @Override
    public String operateSystemCommad() {
        return "windows";
    }

    @Override
    public String limitAccess()  throws LimitException{
        // System.out.println(Thread.currentThread().getName() + "-windows���õ���ɣ�" + semaphore.drainPermits());
        try {
            // semaphore.acquire();// ��ȡ���
            if (semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                atomicLong.incrementAndGet();
                Thread.sleep(150);// ִ������
                semaphore.release();//�ͷ����
                atomicLong.decrementAndGet();
            } else {
                System.out.println("print: ��ǰ�����������࣬���Ժ�����!");
                throw new LimitException("��ǰ�����������࣬���Ժ�����!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        return "windowsִ��������   " + atomicLong.get();
    }
    
    public static void main(String[] args) throws Exception {
        final WindowsGoupImpl impl = new WindowsGoupImpl();
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
