package com.hugo.midware.msgp.example.registry.eureka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ExampleApplicationBoot
 * @Description example application boot
 * @Author hugo
 * @Date 2019-03-27 11:18
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplicationBoot implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationBoot.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(restTemplate.getForObject("http://example-provider/helloworld", String.class));
    }
}
