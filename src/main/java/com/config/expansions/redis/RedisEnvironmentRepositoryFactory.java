package com.config.expansions.redis;

import org.springframework.cloud.config.server.environment.EnvironmentRepositoryFactory;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.jdbc.core.JdbcTemplate;

public class RedisEnvironmentRepositoryFactory implements EnvironmentRepositoryFactory<RedisEnvironmentRepository, RedisEnvironmentProperties> {
    private RedisTemplate redis;

    public RedisEnvironmentRepositoryFactory(RedisTemplate redis) {
        this.redis = redis;
    }

    public RedisEnvironmentRepository build(RedisEnvironmentProperties environmentProperties) {
        return new RedisEnvironmentRepository(this.redis, environmentProperties);
    }
}