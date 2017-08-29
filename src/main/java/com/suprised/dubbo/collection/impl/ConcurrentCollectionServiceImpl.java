package com.suprised.dubbo.collection.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.collection.ConcurrentCollectionService;

@Service(version = "1.0.0")
public class ConcurrentCollectionServiceImpl implements ConcurrentCollectionService {

    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
    
    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    
    @Override
    public void put(String key, String value) {
        System.out.println("add to map key :" + key);
        map.put(key, value);
    }

    @Override
    public void add(String value) {
        System.out.println("add to queue value : " + value);
        queue.add(value);
    }

    @Override
    public ConcurrentHashMap<String, String> getAllMap() {
        return map;
    }

    @Override
    public ConcurrentLinkedQueue<String> getAllQueue() {
        return queue;
    }

}
