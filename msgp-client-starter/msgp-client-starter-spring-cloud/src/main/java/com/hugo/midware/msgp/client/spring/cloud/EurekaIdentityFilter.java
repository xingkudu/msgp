package com.hugo.midware.msgp.client.spring.cloud;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import static com.hugo.midware.msgp.common.utils.Constants.*;

/**
 * @ClassName EurekaIdentityFilter
 * @Description 添加应用标识(用于服务注册认证)
 * @Author hugo
 * @Date 2019-03-28 17:28
 * @Version 1.0
 **/
public class EurekaIdentityFilter extends ClientFilter {

    //application name
    private final String name;

    //application group
    private final String group;

    //application version
    private final String version;

    //application ip
    private final String ip;

    public EurekaIdentityFilter(String name, String group, String version,
                                String ip) {
        this.name = name;
        this.group = group;
        this.version = version;
        this.ip = ip;
    }

    @Override
    public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
        cr.getHeaders().putSingle(INSTANCE_APPLICATION_NAME, this.name);
        cr.getHeaders().putSingle(INSTANCE_APPLICATION_GROUP, this.group);
        cr.getHeaders().putSingle(INSTANCE_APPLICATION_VERSION, this.version);
        cr.getHeaders().putSingle(INSTANCE_APPLICATION_IP, this.ip);
        return getNext().handle(cr);
    }
}
