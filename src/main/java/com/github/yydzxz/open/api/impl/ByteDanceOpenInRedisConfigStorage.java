package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.bean.ByteDanceOpenAuthorizerAccessToken;
import com.github.yydzxz.open.bean.ByteDanceOpenComponentAccessToken;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/06/24
 **/
@Slf4j
public class ByteDanceOpenInRedisConfigStorage extends AbstractByteDanceOpenInRedisConfigStorage {

    private final IByteDanceRedisOps redisOps;

    private Lock accessTokenLockInstance;

    public ByteDanceOpenInRedisConfigStorage(@NonNull IByteDanceRedisOps redisOps, String keyPrefix) {
        this.redisOps = redisOps;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public String getComponentVerifyTicket() {
        return redisOps.getValue(this.componentVerifyTicketKey);
    }

    @Override
    public void setComponentVerifyTicket(String componentVerifyTicket) {
        log.info("redis config set componentVerifyTicketKey, key->{}, value->{}", this.componentVerifyTicketKey, componentVerifyTicket);
        redisOps.setValue(this.componentVerifyTicketKey, componentVerifyTicket, Integer.MAX_VALUE, TimeUnit.SECONDS);
    }

    @Override
    public String getComponentAccessToken() {
        return redisOps.getValue(this.componentAccessTokenKey);
    }

    @Override
    public boolean isComponentAccessTokenExpired() {
        Long expire = redisOps.getExpire(this.componentAccessTokenKey);
        return expire == null || expire < 2;
    }

    @Override
    public void expireComponentAccessToken() {
        redisOps.expire(this.componentAccessTokenKey, 0, TimeUnit.SECONDS);
    }

    @Override
    public void updateComponentAccessToken(ByteDanceOpenComponentAccessToken componentAccessToken) {
        updateComponentAccessToken(componentAccessToken.getComponentAccessToken(), componentAccessToken.getExpiresIn());

    }

//    @Override
//    public ByteDanceMiniProgramConfig getByteDanceMiniProgramConfig(String appId) {
//        ByteDanceMiniProgramConfig config = BYTE_DANCE_MINI_PROGRAM_CONFIG_MAP.get(appId);
//        if(config == null){
//            BYTE_DANCE_MINI_PROGRAM_CONFIG_MAP.put(appId, new ByteDanceMiniProgramRedissonConfigImpl(redisOps, keyPrefix, appId));
//        }
//        return BYTE_DANCE_MINI_PROGRAM_CONFIG_MAP.get(appId);
//    }

    @Override
    public Lock getComponentAccessTokenLock() {
        if (this.accessTokenLockInstance == null) {
            synchronized (this) {
                if (this.accessTokenLockInstance == null) {
                    this.accessTokenLockInstance = getLockByKey("componentAccessTokenLock");
                }
            }
        }
        return this.accessTokenLockInstance;
    }

    @Override
    public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
        redisOps.setValue(this.componentAccessTokenKey, componentAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public boolean autoRefreshToken() {
        return false;
    }

    @Override
    public String getAuthorizerRefreshToken(String appId) {
        return redisOps.getValue(this.getKey(this.authorizerRefreshTokenKey, appId));
    }

    /**
     * authorizer_refresh_token 有效期 1 个月
     * @param appId
     * @param authorizerRefreshToken
     */
    @Override
    public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
        redisOps.setValue(this.getKey(this.authorizerRefreshTokenKey, appId), authorizerRefreshToken, 29, TimeUnit.DAYS);
    }

    @Override
    public String getAuthorizerAccessToken(String appId) {
        return redisOps.getValue(this.getKey(this.authorizerAccessTokenKey, appId));
    }

    @Override
    public boolean isAuthorizerAccessTokenExpired(String appId) {
        Long expire = redisOps.getExpire(this.getKey(this.authorizerAccessTokenKey, appId));
        return expire == null || expire < 2;
    }

    @Override
    public void expireAuthorizerAccessToken(String appId) {
        redisOps.expire(this.getKey(this.authorizerAccessTokenKey, appId), 0, TimeUnit.SECONDS);
    }

    @Override
    public void updateAuthorizerAccessToken(String appId, ByteDanceOpenAuthorizerAccessToken authorizerAccessToken) {
        updateAuthorizerAccessToken(appId, authorizerAccessToken.getAuthorizerAccessToken(),
            authorizerAccessToken.getExpiresIn());    }

    @Override
    public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
        redisOps.setValue(this.getKey(this.authorizerAccessTokenKey, appId), authorizerAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void setByteDanceOpenInfo(String componentAppId, String componentAppSecret, String componentToken, String componentAesKey) {
        setComponentAppId(componentAppId);
        setComponentAppSecret(componentAppSecret);
        setComponentToken(componentToken);
        setComponentAesKey(componentAesKey);
    }

    @Override
    public Lock getLockByKey(String key) {
        return redisOps.getLock(key);
    }

    @Override
    public Lock getAccessTokenLock(String appId) {
        return getLockByKey(this.lockKey.concat(":").concat("accessToken").concat(":").concat(appId));
    }
}
