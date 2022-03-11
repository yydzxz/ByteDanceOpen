package com.github.yydzxz.open.api;


import com.github.yydzxz.open.bean.ByteDanceOpenAuthorizerAccessToken;
import com.github.yydzxz.open.bean.ByteDanceOpenComponentAccessToken;
import java.util.concurrent.locks.Lock;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
public interface IByteDanceOpenConfigStorage {
    String getComponentAppId();

    void setComponentAppId(String componentAppId);

    String getComponentAppSecret();

    void setComponentAppSecret(String componentAppSecret);

    String getComponentToken();

    void setComponentToken(String componentToken);

    String getComponentAesKey();

    void setComponentAesKey(String componentAesKey);

    String getComponentVerifyTicket();

    void setComponentVerifyTicket(String componentVerifyTicket);

    String getComponentAccessToken();

    boolean isComponentAccessTokenExpired();

    void expireComponentAccessToken();

    void updateComponentAccessToken(ByteDanceOpenComponentAccessToken componentAccessToken);

//    ByteDanceMiniProgramConfig getByteDanceMiniProgramConfig(String appId);

    Lock getComponentAccessTokenLock();

    Lock getLockByKey(String key);

    /**
     * 应该是线程安全的
     *
     * @param componentAccessToken 新的accessToken值
     * @param expiresInSeconds     过期时间，以秒为单位
     */
    void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds);

    /**
     * 是否自动刷新token
     */
    boolean autoRefreshToken();

    String getAuthorizerRefreshToken(String appId);

    void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken);

    String getAuthorizerAccessToken(String appId);

    boolean isAuthorizerAccessTokenExpired(String appId);

    /**
     * 强制将access token过期掉
     */
    void expireAuthorizerAccessToken(String appId);

    /**
     * 应该是线程安全的
     *
     * @param authorizerAccessToken 要更新的ByteDanceAccessToken对象
     */
    void updateAuthorizerAccessToken(String appId, ByteDanceOpenAuthorizerAccessToken authorizerAccessToken);

    /**
     * 应该是线程安全的
     *
     * @param authorizerAccessToken 新的accessToken值
     * @param expiresInSeconds      过期时间，以秒为单位
     */
    void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds);

    /**
     * 设置第三方平台基础信息
     * @param componentAppId 第三方平台 appid
     * @param componentAppSecret 第三方平台 appsecret
     * @param componentToken 消息校验Token
     * @param componentAesKey 消息加解密Key
     */
    void setByteDanceOpenInfo(String componentAppId, String componentAppSecret, String componentToken, String componentAesKey);

    Lock getAccessTokenLock(String appId);
}
