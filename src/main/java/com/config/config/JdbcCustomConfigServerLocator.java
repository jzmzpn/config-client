package com.config.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Profile("jdbc")
@Configuration
public class JdbcCustomConfigServerLocator extends AbstractCustomConfigServerLocator {
    @Override
    public Map<String, String> getMap(Environment environment) {
        Map<String, String> properties = new HashMap<>();
        properties.put("password", "test1");
        return properties;
    }
}
