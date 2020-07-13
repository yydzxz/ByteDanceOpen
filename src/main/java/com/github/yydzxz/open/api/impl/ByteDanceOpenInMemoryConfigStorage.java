package com.github.yydzxz.open.api.impl;//package cn.ce.miniprograms.manager.bytedance.open.api.impl;
//
//import cn.ce.miniprograms.manager.bytedance.common.bean.ByteDanceAccessToken;
//import cn.ce.miniprograms.manager.bytedance.miniprogram.config.ByteDanceMiniProgramConfig;
//import cn.ce.miniprograms.manager.bytedance.open.api.IByteDanceOpenConfigStorage;
//import cn.ce.miniprograms.manager.bytedance.open.bean.ByteDanceOpenAuthorizerAccessToken;
//import cn.ce.miniprograms.manager.bytedance.open.bean.ByteDanceOpenComponentAccessToken;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import lombok.Data;
//import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
//
///**
// * @author yangyidian
// * @date 2020/06/23
// **/
//@Data
//public class ByteDanceOpenInMemoryConfigStorage implements IByteDanceOpenConfigStorage {
//    private String componentAppId;
//    private String componentAppSecret;
//    private String componentToken;
//    private String componentAesKey;
//    private String componentVerifyTicket;
//    private String componentAccessToken;
//    private long componentExpiresTime;
//
//    private Map<String, Token> authorizerRefreshTokens = new ConcurrentHashMap<>();
//    private Map<String, Token> authorizerAccessTokens = new ConcurrentHashMap<>();
//    private Map<String, Lock> locks = new ConcurrentHashMap<>();
//
//    @Override
//    public boolean isComponentAccessTokenExpired() {
//        return System.currentTimeMillis() > componentExpiresTime;
//    }
//
//    @Override
//    public void expireComponentAccessToken() {
//        this.componentExpiresTime = 0L;
//    }
//
//    @Override
//    public void updateComponentAccessToken(ByteDanceOpenComponentAccessToken componentAccessToken) {
//        updateComponentAccessToken(componentAccessToken.getComponentAccessToken(), componentAccessToken.getExpiresIn());
//    }
//
//    private Lock accessTokenLockInstance;
//
//    @Override
//    public Lock getComponentAccessTokenLock() {
//        if (this.accessTokenLockInstance == null) {
//            synchronized (this) {
//                if (this.accessTokenLockInstance == null) {
//                    this.accessTokenLockInstance = getLockByKey("componentAccessTokenLock");
//                }
//            }
//        }
//        return this.accessTokenLockInstance;
//    }
//
//    @Override
//    public Lock getLockByKey(String key) {
//        Lock lock = locks.get(key);
//        if (lock == null) {
//            synchronized (this) {
//                lock = locks.get(key);
//                if (lock == null) {
//                    lock = new ReentrantLock();
//                    locks.put(key, lock);
//                }
//            }
//        }
//        return lock;
//    }
//
//
//    @Override
//    public ByteDanceMiniProgramConfig getByteDanceMiniProgramConfig(String appId) {
//        return new ByteDanceOpenInnerConfigStorage(this, appId);
//    }
//
//    @Override
//    public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
//        this.componentAccessToken = componentAccessToken;
//        this.componentExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
//    }
//
//    @Override
//    public void setByteDanceOpenInfo(String componentAppId, String componentAppSecret, String componentToken,
//        String componentAesKey) {
//        setComponentAppId(componentAppId);
//        setComponentAppSecret(componentAppSecret);
//        setComponentToken(componentToken);
//        setComponentAesKey(componentAesKey);
//    }
//
//    @Override
//    public boolean autoRefreshToken() {
//        return true;
//    }
//
//    private String getTokenString(Map<String, Token> map, String key) {
//        Token token = map.get(key);
//        if (token == null || (token.expiresTime != null && System.currentTimeMillis() > token.expiresTime)) {
//            return null;
//        }
//        return token.token;
//    }
//
//    private void expireToken(Map<String, Token> map, String key) {
//        Token token = map.get(key);
//        if (token != null) {
//            token.expiresTime = 0L;
//        }
//    }
//
//    private void updateToken(Map<String, Token> map, String key, String tokenString, Integer expiresInSeconds) {
//        Token token = map.get(key);
//        if (token == null) {
//            token = new Token();
//            map.put(key, token);
//        }
//        token.token = tokenString;
//        if (expiresInSeconds != null) {
//            token.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
//        }
//    }
//
//    @Override
//    public String getAuthorizerRefreshToken(String appId) {
//        return getTokenString(authorizerRefreshTokens, appId);
//    }
//
//    @Override
//    public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
//        updateToken(authorizerRefreshTokens, appId, authorizerRefreshToken, null);
//    }
//
//    @Override
//    public String getAuthorizerAccessToken(String appId) {
//        return getTokenString(authorizerAccessTokens, appId);
//    }
//
//
//    @Override
//    public boolean isAuthorizerAccessTokenExpired(String appId) {
//        return getTokenString(authorizerAccessTokens, appId) == null;
//    }
//
//    @Override
//    public void expireAuthorizerAccessToken(String appId) {
//        expireToken(authorizerAccessTokens, appId);
//    }
//
//    @Override
//    public void updateAuthorizerAccessToken(String appId, ByteDanceOpenAuthorizerAccessToken authorizerAccessToken) {
//        updateAuthorizerAccessToken(appId, authorizerAccessToken.getAuthorizerAccessToken(),
//            authorizerAccessToken.getExpiresIn());
//    }
//
//    @Override
//    public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
//        updateToken(authorizerAccessTokens, appId, authorizerAccessToken, expiresInSeconds);
//    }
//
//
//    private static class Token {
//        private String token;
//        private Long expiresTime;
//    }
//
//    private static class ByteDanceOpenInnerConfigStorage implements ByteDanceMiniProgramConfig {
//        private IByteDanceOpenConfigStorage openConfigStorage;
//        private String appId;
//
//        private final Lock accessTokenLock;
//
//        private ByteDanceOpenInnerConfigStorage(IByteDanceOpenConfigStorage openConfigStorage, String appId) {
//            this.openConfigStorage = openConfigStorage;
//            this.appId = appId;
//            this.accessTokenLock = openConfigStorage.getLockByKey(appId + ":accessTokenLock");
//        }
//
//        @Override
//        public String getAccessToken() {
//            return openConfigStorage.getAuthorizerAccessToken(appId);
//        }
//
//        @Override
//        public Lock getAccessTokenLock() {
//            return this.accessTokenLock;
//        }
//
//        @Override
//        public boolean isAccessTokenExpired() {
//            return openConfigStorage.isAuthorizerAccessTokenExpired(appId);
//        }
//
//        @Override
//        public synchronized void updateAccessToken(ByteDanceAccessToken accessToken) {
//            updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
//        }
//
//        @Override
//        public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
//            openConfigStorage.updateAuthorizerAccessToken(appId, accessToken, expiresInSeconds);
//        }
//
//
//        @Override
//        public String getAppid() {
//            return this.appId;
//        }
//
//        @Override
//        public void expireAccessToken() {
//            openConfigStorage.expireAuthorizerAccessToken(appId);
//        }
//
//
//        @Override
//        public String getSecret() {
//            return null;
//        }
//
//        @Override
//        public String getToken() {
//            return openConfigStorage.getComponentToken();
//        }
//
//        @Override
//        public long getExpiresTime() {
//            return 0;
//        }
//
//        @Override
//        public String getAesKey() {
//            return openConfigStorage.getComponentAesKey();
//        }
//
//        @Override
//        public boolean autoRefreshToken() {
//            return openConfigStorage.autoRefreshToken();
//        }
//
//    }
//}
