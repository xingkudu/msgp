package com.hugo.midware.msgp.example.registry.zookeeper.provider;

import cn.sunline.edsp.midware.rpc.annotation.sdk.annotation.RPCService;
import com.hugo.midware.msgp.example.registry.zookeeper.HelloWorldService;

/**
 * @ClassName HelloWorldServiceImpl
 * @Description TODO
 * @Author hugo
 * @Date 2019-04-12 14:21
 * @Version 1.0
 **/
@RPCService
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String helloWorld() {
        return "hello world!";
    }
}
