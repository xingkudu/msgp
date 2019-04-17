package com.hugo.midware.msgp.example.registry.zookeeper.provider;

import cn.sunline.edsp.midware.rpc.spring.sdk.config.EnableRPC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @ClassName RPCProviderBoot
 * @Description TODO
 * @Author hugo
 * @Date 2019-04-12 14:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableRPC
public class RPCProviderBoot {
    public static void main(String[] args) {
        SpringApplication.run(RPCProviderBoot.class, args);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
