package com.github.yydzxz.open.api.impl;

import cn.hutool.json.JSONUtil;
import com.github.yydzxz.common.error.InvalidAuthorizerRefreshToken;
import com.github.yydzxz.open.api.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.open.api.IByteDanceOpenMaterialService;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.IByteDanceOpenTemplateService;
import com.github.yydzxz.open.api.IExecutable;
import com.github.yydzxz.open.api.IRetryableExecutor;
import com.github.yydzxz.open.api.response.auth.GetPreAuthCodeResponse;
import com.github.yydzxz.open.bean.ByteDanceOpenComponentAccessToken;
import com.github.yydzxz.open.util.ServerVerification;
import com.github.yydzxz.open.util.URIUtil;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.common.error.ByteDanceErrorMsgEnum;
import com.github.yydzxz.open.api.response.auth.GetAuthorizerAccessTokenReponse;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
@Slf4j
public class ByteDanceOpenComponentServiceImpl implements IByteDanceOpenComponentService, IRetryableExecutor {
    private long retrySleepMillis = 1000;

    private int maxRetryTimes = 5;

    private static final Map<String, IByteDanceOpenMiniProgramService> BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP = new ConcurrentHashMap<>();

    private IByteDanceOpenService byteDanceOpenService;

    private IByteDanceOpenTemplateService byteDanceOpenTemplateService;

    private IByteDanceOpenMaterialService byteDanceOpenMaterialService;

    public ByteDanceOpenComponentServiceImpl(IByteDanceOpenService byteDanceOpenService) {
        this.byteDanceOpenService = byteDanceOpenService;
        byteDanceOpenTemplateService = new ByteDanceOpenTemplateServiceImpl(byteDanceOpenService);
        byteDanceOpenMaterialService = new ByteDanceOpenMaterialServiceImpl(byteDanceOpenService);
    }

    @Override
    public IByteDanceOpenService getByteDanceOpenService() {
        return byteDanceOpenService;
    }

    @Override
    public IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage() {
        return byteDanceOpenService.getByteDanceOpenConfigStorage();
    }

    @Override
    public IByteDanceOpenTemplateService getByteDanceOpenTemplateService(){
        return byteDanceOpenTemplateService;
    }

    @Override
    public IByteDanceOpenMiniProgramService getOpenMiniProgramServiceByAppid(String appId) {
        IByteDanceOpenMiniProgramService byteDanceOpenMiniProgramService = BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.get(appId);
        if (byteDanceOpenMiniProgramService == null) {
            synchronized (BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP) {
                byteDanceOpenMiniProgramService = BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.get(appId);
                if (byteDanceOpenMiniProgramService == null) {
                    byteDanceOpenMiniProgramService = new ByteDanceOpenMiniProgramServiceImpl(this, appId);
                    BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.put(appId, byteDanceOpenMiniProgramService);
                }
            }
        }
        return byteDanceOpenMiniProgramService;
    }

    @Override
    public boolean checkSignature(String msgSignature, String tpToken, String timestamp, String nonce, String encrypt) {
        try {
            String newMsgSignature = ServerVerification.getMsgSignature(tpToken, timestamp, nonce, encrypt);
            return newMsgSignature.equals(msgSignature);
        } catch (Exception e) {
            log.error("Checking signature failed, and the reason is :" + e.getMessage());
            log.error("msgSignature=[{}], tpToken=[{}], timestamp=[{}], nonce=[{}], encrypt=[{}]",
                msgSignature, tpToken, timestamp, nonce, encrypt);
            return false;
        }
    }

    @Override
    public String getComponentAccessToken(boolean forceRefresh){
        final IByteDanceOpenConfigStorage config = this.getByteDanceOpenConfigStorage();
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
                + "?component_ticket=" + getByteDanceOpenConfigStorage().getComponentVerifyTicket()
                + "&component_appsecret=" + getByteDanceOpenConfigStorage().getComponentAppSecret()
                + "&component_appid=" + getByteDanceOpenConfigStorage().getComponentAppId();

            String responseContent = this.getByteDanceOpenService().getByteDanceHttpRequestService().get(url);
            ByteDanceOpenComponentAccessToken componentAccessToken = ByteDanceOpenComponentAccessToken.fromJson(responseContent);
            config.updateComponentAccessToken(componentAccessToken);
            return config.getComponentAccessToken();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public IByteDanceOpenConfigStorage getOpenConfigStorage() {
        return byteDanceOpenService.getByteDanceOpenConfigStorage();
    }

    @Override
    public IByteDanceOpenMaterialService getByteDanceOpenMaterialService() {
        return byteDanceOpenMaterialService;
    }

    @Override
    public String getPreAuthUrl(String redirectURI){
        return createPreAuthUrl(redirectURI);
    }

    /**
     * 创建预授权链接
     *
     * @param redirectURI
     * @return
     */
    private String createPreAuthUrl(String redirectURI){
        String responseContent = get(API_CREATE_PRE_AUTH_CODE_URL);
        GetPreAuthCodeResponse response = JSONUtil.toBean(responseContent, GetPreAuthCodeResponse.class);
        StringBuilder preAuthUrl = new StringBuilder(String.format(COMPONENT_LOGIN_PAGE_URL,
            getByteDanceOpenConfigStorage().getComponentAppId(),
            response.getPreAuthCode(),
            URIUtil.encodeURIComponent(redirectURI)));
        String preAuthUrlStr = preAuthUrl.toString();
        return preAuthUrlStr;
    }

    @Override
    public GetAuthorizerAccessTokenReponse getAuthorizerAccessTokenByAuthorizationCode(String authorizationCode){
        String url = API_GET_OAUTH_TOKEN_URL
            + "?authorization_code=" + authorizationCode
            + "&grant_type=app_to_tp_authorization_code";
        GetAuthorizerAccessTokenReponse response = get(url, GetAuthorizerAccessTokenReponse.class);

        if (!StringUtils.isEmpty(response.getAuthorizerAccessToken())) {
            getByteDanceOpenConfigStorage().updateAuthorizerAccessToken(response.getAuthorizerAppid(),
                response.getAuthorizerAccessToken(), response.getExpiresIn());
        }
        if (!StringUtils.isEmpty(response.getAuthorizerRefreshToken())) {
            getByteDanceOpenConfigStorage().setAuthorizerRefreshToken(response.getAuthorizerAppid(), response.getAuthorizerRefreshToken());
        }
        return response;
    }

    @Override
    public String getAuthorizerAccessToken(String appId, boolean forceRefresh) {
        IByteDanceOpenConfigStorage config = getByteDanceOpenConfigStorage();
        if (!config.isAuthorizerAccessTokenExpired(appId) && !forceRefresh) {
            return config.getAuthorizerAccessToken(appId);
        }
        Lock lock = config.getAccessTokenLock(appId);
        lock.lock();
        try {
            if (!config.isAuthorizerAccessTokenExpired(appId) && !forceRefresh) {
                return config.getAuthorizerAccessToken(appId);
            }
            String authorizerRefreshToken = getByteDanceOpenConfigStorage().getAuthorizerRefreshToken(appId);
            if(StringUtils.isEmpty(authorizerRefreshToken)){
                throw new InvalidAuthorizerRefreshToken("authorizerRefreshToken为空，需要重新授权");
            }
            String url = API_GET_OAUTH_TOKEN_URL
                + "?authorizer_refresh_token=" + authorizerRefreshToken
                + "&grant_type=app_to_tp_refresh_token";
            GetAuthorizerAccessTokenReponse response = get(url, GetAuthorizerAccessTokenReponse.class);

            config.updateAuthorizerAccessToken(appId, response.getAuthorizerAccessToken(), response.getExpiresIn());
            config.setAuthorizerRefreshToken(appId, response.getAuthorizerRefreshToken());
            return config.getAuthorizerAccessToken(appId);
        } catch (InvalidAuthorizerRefreshToken e){
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

    @Override
    public <T> T get(String url, Class<T> t) {
        return retryableExecuteRequest(
            (url2, headers, request2, t2) -> {
                return getInternal(url2, t2);
            },
            url,null, null, t);
    }


    private <T> T getInternal(String url, Class<T> t) {
        return executeRequest((uriWithCommonParam, headers, request, t2)->{
            return getByteDanceOpenService().getByteDanceHttpRequestService().get(uriWithCommonParam, t2);
        }, url, null, null, t);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t) {
        return retryableExecuteRequest(
            (url2, headers, request2, t2) -> postInternal(url2, request2, t2),
            url, null, request, t);
    }

    private <T> T postInternal(String url, Object request, Class<T> t) {
        return executeRequest((uriWithCommonParam, headers2, request2, t2)->{
            return getByteDanceOpenService().getByteDanceHttpRequestService().post(uriWithCommonParam, request2, t2);
        }, url, null, request, t);
    }

    @Override
    public <T> T postWithHeaders(String url, Multimap<String,String> headers, Object request, Class<T> t){
        return retryableExecuteRequest((url2, headers2, request2, t2) -> postWithHeadersInternal(url2, headers2, request2, t2),
            url, headers, request, t);
    }

    private <T> T postWithHeadersInternal(String url, Multimap<String,String> headers, Object request, Class<T> t){
        return executeRequest((uriWithCommonParam, headers2, request2, t2)->{
            return getByteDanceOpenService().getByteDanceHttpRequestService().postWithHeaders(uriWithCommonParam, headers2, request2, t2);
        }, url, headers, request, t);
    }

    @Override
    public boolean shouldRetry(ByteDanceError error) {
        return shouldExpireComponentAccessToken(error)
            || ByteDanceErrorMsgEnum.CODE_40000.getCode() == error.getErrno();
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    /**
     * 调用字节跳动服务器接口
     * 统一在url上添加component_access_token 和 component_appid参数
     * @param executable
     * @param url
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    private <T> T executeRequest(IExecutable<T> executable, String url, Multimap<String,String> headers, Object request, Class<T> t){
        String componentAccessToken = getComponentAccessToken(false);
        String componentAppId = getByteDanceOpenConfigStorage().getComponentAppId();
        String uriWithCommonParam = url + (url.contains("?") ? "&" : "?") + "component_access_token=" + componentAccessToken + "&component_appid=" + componentAppId;
        try {
            return executable.execute(uriWithCommonParam, headers, request, t);
        } catch (ByteDanceErrorException e) {
            ByteDanceError error = e.getError();
            /*
             * 发生以下情况时尝试刷新access_token
             * 40009 第三方平台AccessToken已过期
             * 420010 第三方平台AccessToken不正确
             */
            if (shouldExpireComponentAccessToken(error)) {
                // 强制设置componentAccessToken过期，这样在下一次请求里就会刷新componentAccessToken
                Lock lock = this.getByteDanceOpenConfigStorage().getComponentAccessTokenLock();
                lock.lock();
                try {
                    if (StringUtils.equals(componentAccessToken, this.getByteDanceOpenConfigStorage().getComponentAccessToken())) {
                        this.getByteDanceOpenConfigStorage().expireComponentAccessToken();
                    }
                } catch (Exception ex) {
                    this.getByteDanceOpenConfigStorage().expireComponentAccessToken();
                } finally {
                    lock.unlock();
                }
            }
            if (error.getErrno() != null && error.getErrno() != 0) {
                throw new ByteDanceErrorException(error, e);
            }
            return null;
        }
    }

    /**
     * 是否应该让保存的ComponentAccessToken过期
     * @param error
     * @return
     */
    private boolean shouldExpireComponentAccessToken(ByteDanceError error){
        return error.getErrno() == ByteDanceErrorMsgEnum.CODE_40009.getCode()
            || error.getErrno() == ByteDanceErrorMsgEnum.CODE_40010.getCode();
    }
}
