package com.suprised.dubbo.group.impl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.group.LimitException;
import com.suprised.dubbo.group.ServerGroup;

@Service(version = "1.0.0", group = "linux")
public class LinuxGroupImpl implements ServerGroup {

    // 这个许可大小还受dubbo协议的线程池线程大小限制
    private static Semaphore semaphore = new Semaphore(20, false);// 非公平获取许可

    // 也可以使用原子更新操作实现AtomicLong
    private static AtomicLong atomicLong = new AtomicLong(0);
    
    @Override
    public String operateSystemCommad() {
        return "linux";
    }

    @Override
    public String limitAccess()  throws LimitException {
        // System.out.println(Thread.currentThread().getName() + "-linux可用的许可：" + semaphore.drainPermits());
        try {
            semaphore.acquire();// 获取许可 阻塞未获取到许可的线程
            atomicLong.incrementAndGet();
            Thread.sleep(150);// 执行三秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();// 释放许可
            atomicLong.decrementAndGet();
        }
        return "linux执行人数：   " + atomicLong.get();
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
