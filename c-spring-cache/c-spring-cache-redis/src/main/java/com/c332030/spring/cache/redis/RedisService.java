package com.c332030.spring.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.c332030.service.SpringBaseService;
import com.c332030.service.cache.IRedisService;

/**
 * <p>
 * Description: RedisServiceImpl
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Service
public class RedisService extends SpringBaseService implements IRedisService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

}
