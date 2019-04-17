package com.hugo.midware.msgp.client.spring.cloud.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import static com.hugo.midware.msgp.client.spring.cloud.model.ApplicationProperties.PREFIX;

/**
 * @ClassName ApplicationProperties
 * @Description application properties(解析spring.application.name/group/version)
 * @Author hugo
 * @Date 2019-03-28 18:30
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(PREFIX)
public class ApplicationProperties {

    public static final String PREFIX = "spring.application";

    private String name;

    private String group;

    private String version;
}
