package com.hugo.midware.msgp.registry.eureka.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName EurekaAuthenticationException
 * @Description registry or discovery eureka authentication exception
 * @Author hugo
 * @Date 2019-04-08 15:18
 * @Version 1.0
 **/
public class EurekaAuthenticationException extends AuthenticationException {
    public EurekaAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public EurekaAuthenticationException(String msg) {
        super(msg);
    }
}
