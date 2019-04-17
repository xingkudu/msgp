package com.hugo.midware.msgp.registry.eureka.security;

import com.hugo.midware.msgp.registry.boot.model.ServiceCredentials;
import com.hugo.midware.msgp.registry.boot.model.ServiceIdentity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @ClassName InstanceIdentityAuthenticationToken
 * @Description sucurity authentication token， from instance identity(application name, group, version)
 * @Author hugo
 * @Date 2019-03-28 19:46
 * @Version 1.0
 **/
public class InstanceIdentityAuthenticationToken extends AbstractAuthenticationToken {
    private ServiceIdentity principal;
    private ServiceCredentials credentials;
    public InstanceIdentityAuthenticationToken(ServiceIdentity instanceIdentity, ServiceCredentials credentials) {
        super((Collection)null);
        this.principal = instanceIdentity;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public InstanceIdentityAuthenticationToken(ServiceIdentity instanceIdentity, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principal = instanceIdentity;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if(authenticated){
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
