package com.hugo.midware.msgp.registry.eureka.security;

import com.hugo.midware.msgp.common.utils.StringUtil;
import com.hugo.midware.msgp.registry.boot.model.ServiceCredentials;
import com.hugo.midware.msgp.registry.boot.model.ServiceIdentity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.hugo.midware.msgp.common.utils.Constants.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName InstanceIdentityAuthenticationFilter
 * @Description InstanceAuthenticationFilter 过滤instance数据(application)
 * @Author hugo
 * @Date 2019-03-27 18:29
 * @Version 1.0
 **/
public class InstanceIdentityAuthenticationFilter extends OncePerRequestFilter {

    /** Logger available to subclasses. */
    protected final Log logger = LogFactory.getLog(getClass());

    private RememberMeServices rememberMeServices = new NullRememberMeServices();

    private AuthenticationManager authenticationManager;

    private List<RequestMatcher> requiresAuthenticationRequestMatcher = null;

    public InstanceIdentityAuthenticationFilter(AuthenticationManager authenticationManager, String... antMatchers) {
        if(antMatchers.length > 0){
            this.requiresAuthenticationRequestMatcher = Arrays.asList(antMatchers).stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        }
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        if (!requiresAuthentication(request)) {
            chain.doFilter(request, response);
            return;
        }
        if(logger.isInfoEnabled()){
            logger.info("Request is to process InstanceIdentity Authentication");
        }
        try{
            final ServiceIdentity identity = new ServiceIdentity(this.obtainApplicationName(request), this.obtainApplicationGroup(request), this.obtainApplicationVersion(request));
            final ServiceCredentials credentials = new ServiceCredentials(this.obtainApplicationIp(request));
            if(authenticationIsRequired(identity)){
                final Authentication authRequest = new InstanceIdentityAuthenticationToken(identity, credentials);
                Authentication authResult = this.authenticationManager
                        .authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);

                this.rememberMeServices.loginSuccess(request, response, authResult);

            }
        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();
            this.rememberMeServices.loginFail(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean requiresAuthentication(HttpServletRequest request) {
        return requiresAuthenticationRequestMatcher == null ? true : requiresAuthenticationRequestMatcher.stream().anyMatch(requestMatcher -> requestMatcher.matches(request));
    }

    private boolean authenticationIsRequired(ServiceIdentity identity) {
        Authentication existingAuth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        if (existingAuth instanceof InstanceIdentityAuthenticationToken
                && !existingAuth.getPrincipal().equals(identity)) {
            return true;
        }

        if (existingAuth instanceof AnonymousAuthenticationToken) {
            return true;
        }

        return false;
    }

    private String obtainApplicationIp(HttpServletRequest request) {
        return StringUtil.null2Empty(request.getHeader(INSTANCE_APPLICATION_IP));
    }

    private String obtainApplicationName(HttpServletRequest request) {
        return StringUtil.null2Empty(request.getHeader(INSTANCE_APPLICATION_NAME));
    }

    private String obtainApplicationGroup(HttpServletRequest request) {
        return StringUtil.null2Empty(request.getHeader(INSTANCE_APPLICATION_GROUP));
    }

    private String obtainApplicationVersion(HttpServletRequest request) {
        return StringUtil.null2Empty(request.getHeader(INSTANCE_APPLICATION_VERSION));
    }

}
