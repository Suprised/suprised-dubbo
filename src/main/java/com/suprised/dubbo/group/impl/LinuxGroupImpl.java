package com.suprised.dubbo.group.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.suprised.dubbo.group.ServerGroup;

@Service(version="1.0.0", group="linux")
public class LinuxGroupImpl implements ServerGroup {

    @Override
    public String operateSystemCommad() {
        return "linux";
    }

}
