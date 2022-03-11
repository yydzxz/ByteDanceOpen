package com.github.yydzxz.common.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import org.redisson.api.RedissonClient;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
public class RedissonByteDanceRedisOps implements IByteDanceRedisOps {
    private final RedissonClient redissonClient;

    public RedissonByteDanceRedisOps(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public String getValue(String key) {
        Object value = redissonClient.getBucket(key).get();
        return value == null ? null : value.toString();
    }

    @Override
    public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
        if (expire <= 0) {
            redissonClient.getBucket(key).set(value);
        } else {
            redissonClient.getBucket(key).set(value, expire, timeUnit);
        }
    }

    @Override
    public Long getExpire(String key) {
        long expire = redissonClient.getBucket(key).remainTimeToLive();
        if (expire > 0) {
            expire = expire / 1000;
        }
        return expire;
    }

    @Override
    public void expire(String key, int expire, TimeUnit timeUnit) {
        redissonClient.getBucket(key).expire(expire, timeUnit);
    }

    @Override
    public Lock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public boolean deleteKey(String key) {
        return redissonClient.getBucket(key).delete();
    }
}
