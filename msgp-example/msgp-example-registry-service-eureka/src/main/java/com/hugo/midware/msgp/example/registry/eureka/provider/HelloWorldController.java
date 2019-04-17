package com.hugo.midware.msgp.example.registry.eureka.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloWorldController
 * @Description hello world controller(demo)
 * @Author hugo
 * @Date 2019-03-27 13:29
 * @Version 1.0
 **/
@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloworld(){
        return "hello world!";
    }

}
