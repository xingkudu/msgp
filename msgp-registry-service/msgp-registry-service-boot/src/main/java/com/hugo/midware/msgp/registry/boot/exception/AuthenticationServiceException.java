package com.hugo.midware.msgp.registry.boot.exception;

/**
 * @ClassName AuthenticationServiceException
 * @Description 权限校验异常
 * @Author hugo
 * @Date 2019-04-08 14:52
 * @Version 1.0
 **/
public class AuthenticationServiceException extends Exception{
    public AuthenticationServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthenticationServiceException(String msg) {
        super(msg);
    }
}
