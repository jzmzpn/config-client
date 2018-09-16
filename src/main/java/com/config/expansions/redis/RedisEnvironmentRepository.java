package com.config.expansions.redis;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.util.*;

public class RedisEnvironmentRepository implements EnvironmentRepository, Ordered {

    private RedisTemplate<String, Object> redisTemplate;
    private int order;

    public RedisEnvironmentRepository(RedisTemplate redisTemplate, RedisEnvironmentProperties environmentProperties) {
        this.order = environmentProperties.getOrder();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.redisTemplate = redisTemplate;
    }


    public Environment findOne(String application, String profile, String label) {
        String config = application;
        if (StringUtils.isEmpty(label)) {
            label = "master";
        }

        if (StringUtils.isEmpty(profile)) {
            profile = "default";
        }

        if (!profile.startsWith("default")) {
            profile = "default," + profile;
        }

        String[] profiles = StringUtils.commaDelimitedListToStringArray(profile);
        Environment environment = new Environment(application, profiles, label, (String)null, (String)null);
        if (!application.startsWith("application")) {
            config = "application," + application;
        }

        List<String> applications = new ArrayList(new LinkedHashSet(Arrays.asList(StringUtils.commaDelimitedListToStringArray(config))));
        List<String> envs = new ArrayList(new LinkedHashSet(Arrays.asList(profiles)));
        Collections.reverse(applications);
        Collections.reverse(envs);
        Iterator var9 = applications.iterator();
        //坑1：spring boot redis默认采用的serializer是jdk的，而且自己设置的serializer的没有生效，导致通过Redis客户端维护的
        //redis的key 值不对，显示如下
//        127.0.0.1:6379> keys *
//                1) "\xac\xed\x00\x05t\x00\x1dconfig-server-redis-master555"
//        2) "\xac\xed\x00\x05t\x00\x1aconfig-server-redis-master"
//        3) "config-server-redis-master555"
//        4) "\xac\xed\x00\x05t\x00\x1dconfig-server-redis-master444"
//        5) "config-server-redis-master2"
//        6) "\xac\xed\x00\x05t\x00\x1bconfig-server-redis-master1"
//        7) "config-server-redis-master"
//        8) "config-server-default-master"
        //保存的数据类型为哈希
        //HMSET key field1 value1
        //key: application-name
        //field: 对应properties里的key，如spring.application.name; server.port
        //value: 对应参数的值
        while(var9.hasNext()) {
            String app = (String)var9.next();
            Iterator var11 = envs.iterator();

            while(var11.hasNext()) {
                String env = (String)var11.next();
                String key = app + "-" + env + "-" + label;
                if (redisTemplate.hasKey(key)) {
                    Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
                    if (entries != null && !entries.isEmpty()) {
                        environment.add(new PropertySource(app + "-" + env, entries));
                    }
                }
            }
        }
        return environment;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}