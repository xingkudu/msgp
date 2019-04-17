package com.hugo.midware.msgp.client.spring.cloud;

import com.hugo.midware.msgp.client.spring.cloud.model.ApplicationProperties;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.EurekaClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.MutableDiscoveryClientOptionalArgs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * @ClassName MsgpClientAutoConfiguration
 * @Description msgp client(spring cloud) auto configuration
 * @Author hugo
 * @Date 2019-03-28 16:17
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
@ConditionalOnClass(EurekaClientConfig.class)
@AutoConfigureBefore(EurekaClientAutoConfiguration.class)
public class MsgpClientAutoConfiguration {

    private ConfigurableEnvironment env;

    public MsgpClientAutoConfiguration(ConfigurableEnvironment env) {
        this.env = env;
    }

    @Bean
    @ConditionalOnMissingBean(value = AbstractDiscoveryClientOptionalArgs.class, search = SearchStrategy.CURRENT)
    public AbstractDiscoveryClientOptionalArgs discoveryClientOptionalArgs(ApplicationProperties applicationProperties, InetUtils inetUtils) {
        AbstractDiscoveryClientOptionalArgs discoveryClientOptionalArgs = new MutableDiscoveryClientOptionalArgs();
        discoveryClientOptionalArgs.setAdditionalFilters(Arrays.asList(new EurekaIdentityFilter(applicationProperties.getName(), applicationProperties.getGroup(), applicationProperties.getVersion(),
                env.getProperty("eureka.instance.ip-address") == null ? inetUtils.findFirstNonLoopbackHostInfo().getIpAddress() : env.getProperty("eureka.instance.ip-address"))));
        return discoveryClientOptionalArgs;
    }

}
