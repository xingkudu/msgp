package com.hugo.midware.msgp.registry.eureka.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName InstanceGrantedAuthority
 * @Description TODO
 * @Author hugo
 * @Date 2019-04-08 15:32
 * @Version 1.0
 **/
public class InstanceGrantedAuthority implements GrantedAuthority {

    private final String type;

    private final String data;

    public InstanceGrantedAuthority(String type, String data){
        this.type = type;
        this.data = data;
    }

    @Override
    public String getAuthority() {
        return (type + "_" + data).toUpperCase();
    }
}
