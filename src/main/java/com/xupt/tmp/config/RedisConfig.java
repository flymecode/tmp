//package com.xupt.tmp.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@Slf4j
//public class RedisConfig {
//
//    @Value("${spring.redis.isEnable:false}")
//    private boolean isRedisEnable;
//
//    @Autowired
//    private BeanFactory beanFactory;
//
//    @Bean
//    public RedisTemplate<String, Object> initRedisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = null;
//        if (isRedisEnable) {
//            log.info("InitRedisTemplate");
//            redisTemplate = (RedisTemplate<String, Object>) beanFactory.getBean("redisTemplate");
//
//            redisTemplate.setKeySerializer(new StringRedisSerializer());
//            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//            redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
//            redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//            redisTemplate.afterPropertiesSet();
//
//            //用于测试连接
//            log.info("redis client count: {}", redisTemplate.getClientList().size());
//        }
//        return redisTemplate;
//    }
//
//}
