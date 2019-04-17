package com.hugo.midware.msgp.registry.boot.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @ClassName InstanceIdentity
 * @Description instance identity
 * @Author hugo
 * @Date 2019-03-28 19:59
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@ToString
public class ServiceIdentity {

    private String name;

    private String group;

    private String version;

}
