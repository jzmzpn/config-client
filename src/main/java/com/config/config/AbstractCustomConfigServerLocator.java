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
        Map<String, String> properties = getMap();

        CutomPropertySource cutomPropertySource = new CutomPropertySource("cutomPropertySource", properties);
        return cutomPropertySource;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    /*
    * 获取定义中的application名称
    * */
    public String getApplicationName() {
        return environment.getProperty("spring.application.name");
    }

    /*
     * 获取定义中的profile
     * */
    public String getProfile() {
        return environment.getProperty("spring.cloud.config.profile") == null ? "default" : environment.getProperty("spring.cloud.config.profile");
    }

    /*
     * 获取定义中的label
     * */
    public String getLabel() {
        return environment.getProperty("spring.cloud.config.label") == null ? "master" : environment.getProperty("spring.cloud.config.label");
    }

    /*
    * 自定义返回Map类型的配置信息
    * */
    public abstract Map<String, String> getMap();
}
