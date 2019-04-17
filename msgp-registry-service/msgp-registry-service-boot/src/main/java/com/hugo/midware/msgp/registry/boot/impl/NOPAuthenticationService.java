package com.hugo.midware.msgp.registry.boot.impl;

import com.hugo.midware.msgp.registry.boot.AuthenticationService;
import com.hugo.midware.msgp.registry.boot.model.GrantedAuthorities;
import com.hugo.midware.msgp.registry.boot.model.ServiceIdentity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @ClassName NOPAuthenticationService
 * @Description TODO
 * @Author hugo
 * @Date 2019-04-08 16:11
 * @Version 1.0
 **/
@Service
public class NOPAuthenticationService implements AuthenticationService {
    @Override
    public GrantedAuthorities authentication(ServiceIdentity identity) {
        GrantedAuthorities authorities = new GrantedAuthorities();
        authorities.setGrantedDeployNodes(Arrays.asList("10.18.31.30"));
        return authorities;
    }
}
