package com.hugo.midware.msgp.registry.boot;

import com.hugo.midware.msgp.registry.boot.model.GrantedAuthorities;
import com.hugo.midware.msgp.registry.boot.model.ServiceIdentity;


/**
 * @ClassName AuthenticationService
 * @Description 权限认证(注册服务权限认证)
 * @Author hugo
 * @Date 2019-03-28 20:38
 * @Version 1.0
 **/
public interface AuthenticationService {

    GrantedAuthorities authentication(ServiceIdentity identity);

}
