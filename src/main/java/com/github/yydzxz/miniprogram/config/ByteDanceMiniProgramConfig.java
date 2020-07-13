package com.github.yydzxz.miniprogram.config;

import com.github.yydzxz.common.bean.ByteDanceAccessToken;
import java.util.concurrent.locks.Lock;

/**
 * 小程序配置
 * @author yangyidian
 * @date 2020/06/23
 **/
public interface ByteDanceMiniProgramConfig {

    String getAccessToken();

    Lock getAccessTokenLock();

    boolean isAccessTokenExpired();

    /**
     * 强制将access token过期掉
     */
    void expireAccessToken();

    /**
     * 应该是线程安全的
     *
     * @param accessToken 要更新的ByteDanceAccessToken对象
     */
    void updateAccessToken(ByteDanceAccessToken accessToken);

    /**
     * 应该是线程安全的
     *
     * @param accessToken      新的accessToken值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateAccessToken(String accessToken, int expiresInSeconds);

    String getAppid();

    String getSecret();

    String getToken();

    String getAesKey();

    long getExpiresTime();


    /**
     * 是否自动刷新token
     */
    boolean autoRefreshToken();
}
