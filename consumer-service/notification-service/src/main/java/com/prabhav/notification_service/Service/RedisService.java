package com.prabhav.notification_service.Service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    // Key format: "user:notifications:unread:{userId}"
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementUnreadCount(Long userId) {
        String key = "user:notifications:unread:" + userId;
        redisTemplate.opsForValue().increment(key);
    }

    public String getUnreadCount(Long userId) {
        String key = "user:notifications:unread:" + userId;
        return redisTemplate.opsForValue().get(key);
    }

    public void resetUnreadCount(Long userId) {
        String key = "user:notifications:unread:" + userId;
        redisTemplate.opsForValue().set(key, "0");
    }
}
