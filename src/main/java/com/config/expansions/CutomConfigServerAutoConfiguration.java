package com.config.expansions;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.server.config.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnBean({CustomConfigServerConfiguration.Marker.class})
@EnableConfigurationProperties({ConfigServerProperties.class})
@Import({CutomEnvironmentRepositoryConfiguration.class, CompositeConfiguration.class, ResourceRepositoryConfiguration.class, ConfigServerEncryptionConfiguration.class, ConfigServerMvcConfiguration.class})
public class CutomConfigServerAutoConfiguration {
    public CutomConfigServerAutoConfiguration() {
    }
}
