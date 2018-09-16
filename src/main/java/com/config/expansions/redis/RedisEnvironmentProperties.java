package com.config.expansions.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.server.support.EnvironmentRepositoryProperties;

@ConfigurationProperties("spring.cloud.config.server.redis")
public class RedisEnvironmentProperties implements EnvironmentRepositoryProperties {
    private int order = 2147483637;

    public RedisEnvironmentProperties() {

    }
    //order 有什么用
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}