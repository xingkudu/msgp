package com.hugo.midware.msgp.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServerApplicationBoot
 * @Description eureka server application boot
 * @Author hugo
 * @Date 2019-03-26 20:00
 * @Version 1.0
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplicationBoot {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplicationBoot.class, args);
    }
}
