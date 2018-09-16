package com.config.expansions;

import com.config.expansions.redis.RedisEnvironmentProperties;
import com.config.expansions.redis.RedisEnvironmentRepositoryFactory;
import com.config.expansions.redis.RedisRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties({RedisEnvironmentProperties.class})
@Import({RedisRepositoryConfiguration.class})
public class CutomEnvironmentRepositoryConfiguration {
    public CutomEnvironmentRepositoryConfiguration() {
    }

    @Configuration
    @ConditionalOnClass({RedisTemplate.class})
    static class RedisFactoryConfig {
        RedisFactoryConfig() {
        }

        @Autowired
        RedisConnectionFactory redisConnectionFactory;

        /**
         * 实例化 RedisTemplate 对象
         *
         * @return
         */
        @Bean
        public RedisTemplate<String, Object> functionDomainRedisTemplate() {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
            return redisTemplate;
        }

        /**
         * 设置数据存入 redis 的序列化方式
         *
         * @param redisTemplate
         * @param factory
         */
        private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.setConnectionFactory(factory);
        }

        @Bean
        @ConditionalOnBean({RedisTemplate.class})
        public RedisEnvironmentRepositoryFactory jdbcEnvironmentRepositoryFactory(RedisTemplate redisTemplate) {
            return new RedisEnvironmentRepositoryFactory(redisTemplate);
        }
    }

}
