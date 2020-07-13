package com.github.yydzxz.common.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
public interface IByteDanceRedisOps {
    String getValue(String key);

    void setValue(String key, String value, int expire, TimeUnit timeUnit);

    Long getExpire(String key);

    void expire(String key, int expire, TimeUnit timeUnit);

    Lock getLock(String key);

}
