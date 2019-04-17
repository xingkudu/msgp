package com.hugo.midware.msgp.example.registry.eureka.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ExampleApplicationBoot
 * @Description example application boot
 * @Author hugo
 * @Date 2019-03-27 11:18
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplicationBoot {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplicationBoot.class, args);
    }
}
