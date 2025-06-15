package com.ppdd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair
                        .fromSerializer(RedisSerializer.json())) // 序列化方式json
                .entryTtl(Duration.ofDays(1L)) // 缓存有效期1天
                .disableCachingNullValues(); // 不缓存空值

        return redisCacheConfiguration;
    }
}
