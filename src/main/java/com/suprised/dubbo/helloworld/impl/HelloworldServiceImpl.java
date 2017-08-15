package com.suprised.dubbo.helloworld.impl;

import com.suprised.dubbo.helloworld.HelloworldService;

public class HelloworldServiceImpl implements HelloworldService {

    public String sayHi(String name) {
        System.out.println("hi," + name);
        return "hi, " + name;
    }

}
