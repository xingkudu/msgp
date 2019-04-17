package com.hugo.midware.msgp.registry.eureka.security;

import com.hugo.midware.msgp.registry.boot.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import java.util.Arrays;

/**
 * @ClassName ReigstryEurekaSecurityConfigurer
 * @Description registry security
 * @Author hugo
 * @Date 2019-03-27 18:19
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
public class ReigstryEurekaSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(new InstanceIdentityProvider(authenticationService)), super.authenticationManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.addFilterBefore(getInstanceIdentityAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

    private Filter getInstanceIdentityAuthenticationFilter() throws Exception {
        Filter authenticationProcessingFilter = new InstanceIdentityAuthenticationFilter(this.authenticationManager(), "/eureka/apps/**", "/eureka/peerreplication/**");
        return authenticationProcessingFilter;
    }
}
