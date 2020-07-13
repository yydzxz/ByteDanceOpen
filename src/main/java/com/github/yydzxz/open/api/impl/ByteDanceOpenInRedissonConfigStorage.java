package com.github.yydzxz.open.api.impl;//package cn.ce.miniprograms.service.bytedance.impl;
//
//import cn.ce.miniprograms.service.bytedance.redis.IByteDanceRedisOps;
//import cn.ce.miniprograms.service.bytedance.redis.impl.RedissonByteDanceRedisOps;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//import lombok.NonNull;
//import org.redisson.api.RedissonClient;
//
///**
// * @author yangyidian
// * @date 2020/06/23
// **/
//public class ByteDanceOpenInRedissonConfigStorage extends AbstractByteDanceOpenInRedisConfigStorage {
//    private final IByteDanceRedisOps redisOps;
//
//    public ByteDanceOpenInRedissonConfigStorage(@NonNull RedissonClient redissonClient, String keyPrefix) {
//        this(new RedissonByteDanceRedisOps(redissonClient), keyPrefix);
//    }
//
//    public ByteDanceOpenInRedissonConfigStorage(@NonNull RedissonClient redissonClient) {
//        this(redissonClient, null);
//    }
//
//    private ByteDanceOpenInRedissonConfigStorage(@NonNull IByteDanceRedisOps redisOps, String keyPrefix) {
//        this.redisOps = redisOps;
//        this.keyPrefix = keyPrefix;
//    }
//
//    @Override
//    public String getComponentVerifyTicket() {
//        return redisOps.getValue(this.componentVerifyTicketKey);
//    }
//
//    @Override
//    public void setComponentVerifyTicket(String componentVerifyTicket) {
//        redisOps.setValue(this.componentVerifyTicketKey, componentVerifyTicket, Integer.MAX_VALUE, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public String getComponentAccessToken() {
//        return redisOps.getValue(this.componentAccessTokenKey);
//    }
//
//    @Override
//    public boolean isComponentAccessTokenExpired() {
//        Long expire = redisOps.getExpire(this.componentAccessTokenKey);
//        return expire == null || expire < 2;
//    }
//
//    @Override
//    public void expireComponentAccessToken() {
//        redisOps.expire(this.componentAccessTokenKey, 0, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
//        redisOps.setValue(this.componentAccessTokenKey, componentAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public String getAuthorizerRefreshToken(String appId) {
//        return redisOps.getValue(this.getKey(this.authorizerRefreshTokenKey, appId));
//    }
//
//    @Override
//    public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
//        redisOps.setValue(this.getKey(this.authorizerRefreshTokenKey, appId), authorizerRefreshToken, 0, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public String getAuthorizerAccessTokenByAuthorizationCode(String appId) {
//        return redisOps.getValue(this.getKey(this.authorizerAccessTokenKey, appId));
//    }
//
//    @Override
//    public boolean isAuthorizerAccessTokenExpired(String appId) {
//        Long expire = redisOps.getExpire(this.getKey(this.authorizerAccessTokenKey, appId));
//        return expire == null || expire < 2;
//    }
//
//    @Override
//    public void expireAuthorizerAccessToken(String appId) {
//        redisOps.expire(this.getKey(this.authorizerAccessTokenKey, appId), 0, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
//        redisOps.setValue(this.getKey(this.authorizerAccessTokenKey, appId), authorizerAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public Lock getLockByKey(String key) {
//        return redisOps.getLock(key);
//    }
//}
