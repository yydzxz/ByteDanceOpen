package com.github.yydzxz.miniprogram.config;

import com.github.yydzxz.common.bean.ByteDanceAccessToken;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.redis.RedissonByteDanceRedisOps;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
@Data
public class ByteDanceMiniProgramRedissonConfigImpl implements ByteDanceMiniProgramConfig{
    protected final static String LOCK_KEY = "bytedance_miniprogram_lock:";
    protected final static String MINI_PROGRAM_ACCESS_TOKEN_KEY = "bytedance_miniprogram_access_token_key:";

    protected volatile String appid;
    protected volatile String token;
    private volatile String secret;
    private volatile String accessToken;
    private volatile String aesKey;
    private volatile long expiresTime;

    /**
     * redis 存储的 key 的前缀，可为空
     */
    protected String keyPrefix;
    protected String accessTokenKey;
    protected String lockKey;

    private final IByteDanceRedisOps redisOps;

    public ByteDanceMiniProgramRedissonConfigImpl(@NonNull RedissonClient redissonClient, String keyPrefix, String appid) {
        this(new RedissonByteDanceRedisOps(redissonClient), keyPrefix, appid);
    }

    public ByteDanceMiniProgramRedissonConfigImpl(@NonNull RedissonClient redissonClient, String appid) {
        this(redissonClient, null, appid);
    }

    public ByteDanceMiniProgramRedissonConfigImpl(@NonNull IByteDanceRedisOps redisOps, String keyPrefix, String appid) {
        this.redisOps = redisOps;
        this.keyPrefix = keyPrefix;
        setAppid(appid);
    }

    public void setAppid(String appid) {
        this.appid = appid;
        String prefix = StringUtils.isBlank(keyPrefix) ? "" :
            (StringUtils.endsWith(keyPrefix, ":") ? keyPrefix : (keyPrefix + ":"));
        lockKey = prefix + LOCK_KEY.concat(appid);
        accessTokenKey = prefix + MINI_PROGRAM_ACCESS_TOKEN_KEY.concat(appid);
    }

    protected Lock getLockByKey(String key) {
        return redisOps.getLock(key);
    }

    @Override
    public Lock getAccessTokenLock() {
        return getLockByKey(this.lockKey.concat(":").concat("accessToken"));
    }


    @Override
    public String getAccessToken() {
        return redisOps.getValue(this.accessTokenKey);
    }

    @Override
    public boolean isAccessTokenExpired() {
        Long expire = redisOps.getExpire(this.accessTokenKey);
        return expire == null || expire < 2;
    }

    @Override
    public void updateAccessToken(ByteDanceAccessToken accessToken) {
        redisOps.setValue(this.accessTokenKey, accessToken.getAccessToken(), accessToken.getExpiresIn(), TimeUnit.SECONDS);
    }

    @Override
    public void updateAccessToken(String accessToken, int expiresInSeconds) {
        redisOps.setValue(this.accessTokenKey, accessToken, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean autoRefreshToken() {
        return true;
    }

    @Override
    public void expireAccessToken() {
        redisOps.expire(this.accessTokenKey, 0, TimeUnit.SECONDS);
    }
}
