package com.suprised.dubbo.group.impl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.group.LimitException;
import com.suprised.dubbo.group.ServerGroup;

@Service(version="1.0.0", group="windows")
public class WindowsGoupImpl implements ServerGroup {

    private static Semaphore semaphore = new Semaphore(10,false);// 非公平获取许可
    // 也可以使用原子更新操作实现AtomicLong
    
    private static AtomicLong atomicLong = new AtomicLong(0);
    
    @Override
    public String operateSystemCommad() {
        return "windows";
    }

    @Override
    public String limitAccess()  throws LimitException{
        // System.out.println(Thread.currentThread().getName() + "-windows可用的许可：" + semaphore.drainPermits());
        try {
            // semaphore.acquire();// 获取许可
            if (semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                atomicLong.incrementAndGet();
                Thread.sleep(150);// 执行三秒
                semaphore.release();//释放许可
                atomicLong.decrementAndGet();
            } else {
                System.out.println("print: 当前访问人数过多，请稍后再试!");
                throw new LimitException("当前访问人数过多，请稍后再试!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        return "windows执行人数：   " + atomicLong.get();
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
