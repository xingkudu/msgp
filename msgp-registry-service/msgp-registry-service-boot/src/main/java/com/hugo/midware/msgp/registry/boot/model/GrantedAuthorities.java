package com.hugo.midware.msgp.registry.boot.model;

import com.hugo.midware.msgp.common.utils.StringUtil;
import com.hugo.midware.msgp.registry.boot.exception.AuthenticationServiceException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GrantedAuthorities
 * @Description 准许的权限
 * @Author hugo
 * @Date 2019-04-08 14:43
 * @Version 1.0
 **/
@Data
public class GrantedAuthorities {

    private List<String> grantedDeployNodes;

    public void auth(ServiceCredentials credentials) throws AuthenticationServiceException {
        if(grantedDeployNodes == null){
            throw new AuthenticationServiceException("service instance cannot registry, please check service instance registry authority");
        }
        if(StringUtil.isNotEmpty(credentials.getIp()) && !grantedDeployNodes.contains(credentials.getIp())){
            throw new AuthenticationServiceException("service instance don't have registry authority");
        }
    }

}
