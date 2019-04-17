package com.hugo.midware.msgp.registry.eureka;

import com.hugo.midware.msgp.registry.boot.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName RegistryServiceEurekaApplicationBoot
 * @Description eureka server boot stater
 * @Author hugo
 * @Date 2019-03-26 19:06
 * @Version 1.0
 **/
@EnableEurekaServer
@SpringBootApplication(scanBasePackageClasses = {AuthenticationService.class, RegistryServiceEurekaApplicationBoot.class})
public class RegistryServiceEurekaApplicationBoot {
    public static void main(String[] args) {
        SpringApplication.run(RegistryServiceEurekaApplicationBoot.class, args);
    }
}
