package com.config.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public abstract class AbstractCustomConfigServerLocator implements PropertySourceLocator {
    private Environment environment;

    @Override
    public PropertySource<?> locate(Environment environment) {
        this.environment = environment;
        Map<String, String> properties = getMap(environment);

        CutomPropertySource cutomPropertySource = new CutomPropertySource("cutomPropertySource", properties);
        return cutomPropertySource;
    }

    public String getApplicationName() {
        return environment.getProperty("spring.application.name");
    }

    public String getProfile() {
        return environment.getProperty("spring.cloud.config.profile") == null ? "default" : environment.getProperty("spring.cloud.config.profile");
    }

    public String getLabel() {
        return environment.getProperty("spring.cloud.config.label") == null ? "master" : environment.getProperty("spring.cloud.config.label");
    }

    public abstract Map<String, String> getMap(Environment environment);
}
