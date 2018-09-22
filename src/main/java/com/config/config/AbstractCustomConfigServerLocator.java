package com.config.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public abstract class AbstractCustomConfigServerLocator implements PropertySourceLocator {

    private String profile = "default";

    private String label = "master";

    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String, String> properties = getMap(environment);

        CutomPropertySource cutomPropertySource = new CutomPropertySource("cutomPropertySource", properties);
        return cutomPropertySource;
    }

    public abstract Map<String, String> getMap(Environment environment);
}
