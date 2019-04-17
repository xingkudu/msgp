package com.hugo.midware.msgp.registry.eureka.security;

import com.hugo.midware.msgp.registry.boot.AuthenticationService;
import com.hugo.midware.msgp.registry.boot.exception.AuthenticationServiceException;
import com.hugo.midware.msgp.registry.boot.model.GrantedAuthorities;
import com.hugo.midware.msgp.registry.boot.model.ServiceCredentials;
import com.hugo.midware.msgp.registry.boot.model.ServiceIdentity;
import com.hugo.midware.msgp.registry.eureka.exception.EurekaAuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName InstanceIdentityProvider
 * @Description instance identity authentication provider
 * @Author hugo
 * @Date 2019-03-28 20:32
 * @Version 1.0
 **/
public class InstanceIdentityProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    public InstanceIdentityProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        InstanceIdentityAuthenticationToken authenticationToken = (InstanceIdentityAuthenticationToken)authentication;
        try{
            GrantedAuthorities authorities = authenticationService.authentication((ServiceIdentity) authenticationToken.getPrincipal());
            authorities.auth((ServiceCredentials) authenticationToken.getCredentials());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if(authorities.getGrantedDeployNodes() != null){
                grantedAuthorities.addAll(authorities.getGrantedDeployNodes().stream().map(u -> new InstanceGrantedAuthority("deploy_node", u)).collect(Collectors.toList()));
            }
            return new InstanceIdentityAuthenticationToken((ServiceIdentity)authenticationToken.getPrincipal(), grantedAuthorities);
        } catch (AuthenticationServiceException ase){
            throw new EurekaAuthenticationException(ase.getMessage(), ase);
        } catch (Exception e){
            throw new EurekaAuthenticationException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return InstanceIdentityAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
