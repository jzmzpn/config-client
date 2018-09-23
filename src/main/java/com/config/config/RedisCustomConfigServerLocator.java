package com.config.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Profile("redis")
@Configuration
public class RedisCustomConfigServerLocator extends AbstractCustomConfigServerLocator {

    @Override
    public Map<String, String> getMap(Environment environment) {
        String applicationName = this.getApplicationName();

        String profile = this.getProfile();

        String label = this.getLabel();
        String redisHost = environment.getProperty("redis.host") == null ? "127.0.0.1" : environment.getProperty("redis.host");
        int port = environment.getProperty("redis.host") == null ? 6379 : Integer.parseInt(environment.getProperty("redis.host"));
        Jedis jedis = new Jedis(redisHost, port);
        try {
            String key = applicationName + "-" + profile + "-" + label;
            Map<String, String> properties = jedis.hgetAll(key);
            if (!properties.isEmpty()) {
                return properties;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return new HashMap<>();
    }
}
