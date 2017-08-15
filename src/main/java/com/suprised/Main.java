package com.suprised;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动入口
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-server.xml" });
        context.start();

        System.in.read(); // 按任意键退出
    }

}
