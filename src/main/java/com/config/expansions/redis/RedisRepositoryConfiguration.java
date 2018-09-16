package com.config.expansions.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
//通过spring.profiles.active=redis去启用redis作为配置中心的数据源
@Profile({"redis"})
//ConditionalOnClass这个地方遇到了一个奇怪的问题，当ConditionalOnClass指定的类和RedisRepositoryConfiguration
//在同一个路径下时，该类没有被实例化
@ConditionalOnClass({RedisTemplate.class})
public class RedisRepositoryConfiguration {
    RedisRepositoryConfiguration() {
    }

    /*
    * redisTemplate实现的先后顺序问题导致RedisEnvironmentRepository没有实例化为Bean
    * */
    @Bean
    @ConditionalOnBean({RedisTemplate.class})
    public RedisEnvironmentRepository redisEnvironmentRepository(RedisEnvironmentRepositoryFactory factory, RedisEnvironmentProperties environmentProperties) {
        return factory.build(environmentProperties);
    }
}