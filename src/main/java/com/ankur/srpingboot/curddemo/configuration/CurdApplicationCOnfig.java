package com.ankur.srpingboot.curddemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class CurdApplicationCOnfig extends CachingConfigurerSupport {

    @Value("${redis.host}")
    private String redisHostName;

    @Value("${redis.port}")
    private int redisPort;

   /* @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        System.err.println("In cache cacheConfiguration");
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));


    }*/


   /* @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        System.err.println("In cache cacheManager");
        RedisSerializationContext.SerializationPair<Object> jsonSerializer = RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer());

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(jsonSerializer);

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(config)
                .withCacheConfiguration("employees", RedisCacheConfiguration.defaultCacheConfig())
                .build();
    }*/


    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

        return RedisCacheManager.builder(jedisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("employees", myDefaultCacheConfig(Duration.ofMinutes(5)))
                .withCacheConfiguration("employee", myDefaultCacheConfig(Duration.ofMinutes(1)))
                .build();
    }

    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {
        System.err.println("In cache redisTemplate");
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        setRedisTemplateConfigValues(redisConnectionFactory, template);
        return template;
    }

    private <T> void setRedisTemplateConfigValues(final RedisConnectionFactory redisConnectionFactory,
                                                  final RedisTemplate<String, T> template) {

        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        System.err.println("In cache jedisConnectionFactory");
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHostName);
        factory.setPort(redisPort);
        factory.setUsePool(true);
        return factory;
    }
}
