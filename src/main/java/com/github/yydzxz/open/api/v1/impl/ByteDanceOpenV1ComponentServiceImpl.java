package com.github.yydzxz.open.api.v1.impl;

import cn.hutool.core.util.StrUtil;
import com.github.yydzxz.common.error.InvalidAuthorizerRefreshToken;
import com.github.yydzxz.open.api.impl.AbstractByteDanceOpenComponentService;
import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1ComponentService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MaterialService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1TemplateService;
import com.github.yydzxz.open.api.v1.response.auth.GetAuthorizerAccessTokenResponse;
import com.github.yydzxz.open.api.v1.response.auth.GetPreAuthCodeResponse;
import com.github.yydzxz.open.bean.ByteDanceOpenComponentAccessToken;
import com.github.yydzxz.open.util.URIUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
@Slf4j
public class ByteDanceOpenV1ComponentServiceImpl extends AbstractByteDanceOpenComponentService implements IByteDanceOpenV1ComponentService {
    private long retrySleepMillis = 1000;

    private int maxRetryTimes = 5;

    private IByteDanceOpenV1TemplateService byteDanceOpenV1TemplateService;

    private IByteDanceOpenV1MaterialService byteDanceOpenV1MaterialService;

    private static final Map<String, IByteDanceOpenV1MiniProgramService> BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP = new ConcurrentHashMap<>();

    @Override
    public IByteDanceOpenV1MiniProgramService getByteDanceOpenMiniProgramServiceByAppid(String appId) {
        IByteDanceOpenV1MiniProgramService byteDanceOpenMiniProgramService = BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.get(appId);
        if (byteDanceOpenMiniProgramService == null) {
            synchronized (BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP) {
                byteDanceOpenMiniProgramService = BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.get(appId);
                if (byteDanceOpenMiniProgramService == null) {
                    byteDanceOpenMiniProgramService = new ByteDanceOpenV1MiniProgramServiceImpl(this, appId);
                    BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.put(appId, byteDanceOpenMiniProgramService);
                }
            }
        }
        return byteDanceOpenMiniProgramService;
    }

    public ByteDanceOpenV1ComponentServiceImpl(IByteDanceOpenService byteDanceOpenService) {
        super(byteDanceOpenService);
        byteDanceOpenV1TemplateService = new ByteDanceOpenV1TemplateServiceImpl(byteDanceOpenService);
        byteDanceOpenV1MaterialService = new ByteDanceOpenV1MaterialServiceImpl(byteDanceOpenService);
    }

    @Override
    public IByteDanceOpenV1MaterialService getByteDanceOpenMaterialService() {
        return byteDanceOpenV1MaterialService;
    }

    @Override
    public IByteDanceOpenV1TemplateService getByteDanceOpenTemplateService() {
        return byteDanceOpenV1TemplateService;
    }

    @Override
    public String getComponentAccessToken(boolean forceRefresh){
        final IByteDanceOpenConfigStorage config = getByteDanceOpenService().getByteDanceOpenConfigStorage();
        if (!config.isComponentAccessTokenExpired() && !forceRefresh) {
            return config.getComponentAccessToken();
        }
        Lock lock = config.getComponentAccessTokenLock();
        lock.lock();
        try {
            if (!config.isComponentAccessTokenExpired() && !forceRefresh) {
                return config.getComponentAccessToken();
            }

            String url = API_COMPONENT_TOKEN_URL
                + "?component_ticket=" + getByteDanceOpenService().getByteDanceOpenConfigStorage().getComponentVerifyTicket()
                + "&component_appsecret=" + getByteDanceOpenService().getByteDanceOpenConfigStorage().getComponentAppSecret()
                + "&component_appid=" + getByteDanceOpenService().getByteDanceOpenConfigStorage().getComponentAppId();

            ByteDanceOpenComponentAccessToken componentAccessToken = this.getByteDanceOpenService().getByteDanceHttpRequestService().get(url, ByteDanceOpenComponentAccessToken.class);
            config.updateComponentAccessToken(componentAccessToken);
            return config.getComponentAccessToken();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public String getPreAuthUrl(String redirectURI){
        GetPreAuthCodeResponse response = get(API_CREATE_PRE_AUTH_CODE_URL, GetPreAuthCodeResponse.class);
        StringBuilder preAuthUrl = new StringBuilder(String.format(COMPONENT_LOGIN_PAGE_URL,
            getByteDanceOpenService().getByteDanceOpenConfigStorage().getComponentAppId(),
            response.getPreAuthCode(),
            URIUtil.encodeURIComponent(redirectURI)));
        return preAuthUrl.toString();
    }

    @Override
    public GetAuthorizerAccessTokenResponse getAuthorizerAccessTokenByAuthorizationCode(String authorizationCode){
        String url = API_GET_OAUTH_TOKEN_URL
            + "?authorization_code=" + authorizationCode
            + "&grant_type=app_to_tp_authorization_code";
        GetAuthorizerAccessTokenResponse response = get(url, GetAuthorizerAccessTokenResponse.class);

        if (!StrUtil.isEmpty(response.getAuthorizerAccessToken())) {
            getByteDanceOpenService().getByteDanceOpenConfigStorage().updateAuthorizerAccessToken(response.getAuthorizerAppid(),
                response.getAuthorizerAccessToken(), response.getExpiresIn());
        }
        if (!StrUtil.isEmpty(response.getAuthorizerRefreshToken())) {
            getByteDanceOpenService().getByteDanceOpenConfigStorage().setAuthorizerRefreshToken(response.getAuthorizerAppid(), response.getAuthorizerRefreshToken());
        }
        return response;
    }

    @Override
    public String getAuthorizerAccessToken(String appId, boolean forceRefresh) {
        IByteDanceOpenConfigStorage config = getByteDanceOpenService().getByteDanceOpenConfigStorage();
        if (!config.isAuthorizerAccessTokenExpired(appId) && !forceRefresh) {
            return config.getAuthorizerAccessToken(appId);
        }
        Lock lock = config.getAccessTokenLock(appId);
        lock.lock();
        try {
            if (!config.isAuthorizerAccessTokenExpired(appId) && !forceRefresh) {
                return config.getAuthorizerAccessToken(appId);
            }
            String authorizerRefreshToken = getByteDanceOpenService().getByteDanceOpenConfigStorage().getAuthorizerRefreshToken(appId);
            if(StrUtil.isEmpty(authorizerRefreshToken)){
                throw new InvalidAuthorizerRefreshToken("authorizerRefreshToken为空，需要重新授权");
            }
            String url = API_GET_OAUTH_TOKEN_URL
                + "?authorizer_refresh_token=" + authorizerRefreshToken
                + "&grant_type=app_to_tp_refresh_token";
            GetAuthorizerAccessTokenResponse response = get(url, GetAuthorizerAccessTokenResponse.class);

            config.updateAuthorizerAccessToken(appId, response.getAuthorizerAccessToken(), response.getExpiresIn());
            config.setAuthorizerRefreshToken(appId, response.getAuthorizerRefreshToken());
            return config.getAuthorizerAccessToken(appId);
        } catch (InvalidAuthorizerRefreshToken e){
            log.error("AuthorizerRefreshToken 不合法, 请尝试解绑后重新授权");
            throw e;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(appId + "获取 AuthorizerAccessToken 失败, 请尝试解绑后重新授权");
        }finally {
            lock.unlock();
        }
    }

    @Override
    public long getRetrySleepMillis() {
        return retrySleepMillis;
    }

    @Override
    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setRetrySleepMillis(long retrySleepMillis) {
        this.retrySleepMillis = retrySleepMillis;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

}
