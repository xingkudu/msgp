package com.hugo.midware.msgp.example.registry.zookeeper.consumer;

import cn.sunline.edsp.midware.rpc.annotation.sdk.annotation.Reference;
import cn.sunline.edsp.midware.rpc.spring.sdk.config.EnableRPC;
import com.hugo.midware.msgp.example.registry.zookeeper.HelloWorldService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * @ClassName RPCConsumerBoot
 * @Description TODO
 * @Author hugo
 * @Date 2019-04-12 14:32
 * @Version 1.0
 **/
@EnableRPC
@SpringBootApplication
public class RPCConsumerBoot {

    @Reference(application = "edsp-provider", group = "g01", version = "1.0.0")
    private HelloWorldService helloWorldService;

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(RPCConsumerBoot.class, args);

        RPCConsumerBoot consumer = app.getBean(RPCConsumerBoot.class);
        System.out.println(consumer.helloWorldService.helloWorld());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
