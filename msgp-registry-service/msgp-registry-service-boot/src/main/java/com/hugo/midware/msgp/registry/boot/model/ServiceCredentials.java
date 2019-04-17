package com.hugo.midware.msgp.registry.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName ServiceCredentials
 * @Description service credentials
 * FIXME 暂只包含ip地址
 * @Author hugo
 * @Date 2019-03-28 20:43
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class ServiceCredentials {

    private String ip;

}
