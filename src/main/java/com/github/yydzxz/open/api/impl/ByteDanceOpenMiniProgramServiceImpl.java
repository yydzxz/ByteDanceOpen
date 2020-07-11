package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.api.IExecutable;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.common.error.ByteDanceMiniProgramErrorMsgEnum;
import com.github.yydzxz.miniprogram.config.ByteDanceMiniProgramConfig;
import com.github.yydzxz.open.api.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramCodeService;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramInfoService;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;
import com.github.yydzxz.open.api.IRetryableExecutor;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author yangyidian
 * @date 2020/06/24
 **/
@Slf4j
public class ByteDanceOpenMiniProgramServiceImpl implements IByteDanceOpenMiniProgramService, IRetryableExecutor {
    private IByteDanceOpenComponentService byteDanceOpenComponentService;

    private ByteDanceMiniProgramConfig byteDanceMiniProgramConfig;

    private String appId;

    private IByteDanceOpenMiniProgramCodeService byteDanceOpenMiniProgramCodeService;

    private IByteDanceOpenMiniProgramInfoService byteDanceOpenMiniProgramInfoService;

    private int retrySleepMillis = 1000;

    private int maxRetryTimes = 5;

    public ByteDanceOpenMiniProgramServiceImpl(IByteDanceOpenComponentService byteDanceOpenComponentService, String appId, ByteDanceMiniProgramConfig byteDanceMiniProgramConfig) {
        this.byteDanceOpenComponentService = byteDanceOpenComponentService;
        this.appId = appId;
        this.byteDanceMiniProgramConfig = byteDanceMiniProgramConfig;
        this.byteDanceOpenMiniProgramCodeService = new ByteDanceOpenMiniProgramCodeServiceImpl(this);
        this.byteDanceOpenMiniProgramInfoService = new ByteDanceOpenMiniProgramInfoServiceImpl(this);
    }

    @Override
    public IByteDanceOpenComponentService getByteDanceOpenComponentService(){
        return byteDanceOpenComponentService;
    }

    @Override
    public String getAccessToken() {
        return getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean forceRefresh) {
        return getByteDanceOpenComponentService().getAuthorizerAccessToken(appId, forceRefresh);
    }

    @Override
    public long getRetrySleepMillis() {
        return this.retrySleepMillis;
    }

    @Override
    public int getMaxRetryTimes() {
        return this.maxRetryTimes;
    }

    public void setRetrySleepMillis(int retrySleepMillis) {
        this.retrySleepMillis = retrySleepMillis;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    @Override
    public ByteDanceMiniProgramConfig getByteDanceMiniProgramConfig() {
        return byteDanceMiniProgramConfig;
    }

    @Override
    public void setByteDanceMiniProgramConfig(ByteDanceMiniProgramConfig byteDanceMiniProgramConfig) {
        this.byteDanceMiniProgramConfig = byteDanceMiniProgramConfig;
    }

    @Override
    public IByteDanceOpenMiniProgramCodeService getByteDanceOpenMiniProgramCodeService() {
        return byteDanceOpenMiniProgramCodeService;
    }

    @Override
    public IByteDanceOpenMiniProgramInfoService getByteDanceOpenMiniProgramInfoService() {
        return byteDanceOpenMiniProgramInfoService;
    }

    @Override
    public <T> T get(String url, Class<T> t){
        return retryableExecuteRequest((String url2 , Object request2 , Class<T> t2)->{
                return getInternal(url2, t2);
            },url, null, t);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t){
        return retryableExecuteRequest((String url2 , Object request2 , Class<T> t2)->{
            return postInternal(url2, request2, t2);
        },url, request, t);
    }

    private <T> T postInternal(String url, Object request, Class<T> t){
        return executeRequest(
            (String uriWithCommonParam, Object request2, Class<T> t2) -> {
                return getByteDanceOpenComponentService().getByteDanceOpenService().getByteDanceHttpRequestService().post(uriWithCommonParam, request2, t2);
            },url, request, t
        );
    }

    private <T> T getInternal(String url, Class<T> t){
        return executeRequest(
            (String uriWithCommonParam, Object request2, Class<T> t2) -> {
                return getByteDanceOpenComponentService().getByteDanceOpenService().getByteDanceHttpRequestService().get(uriWithCommonParam, t2);
            },url, null, t
        );
    }

    private <T> T executeRequest(IExecutable<T> executable, String url, Object request, Class<T> t){
        String accessToken = getAccessToken(false);
        String componentAppid = getByteDanceOpenComponentService().getByteDanceOpenConfigStorage().getComponentAppId();
        T response = null;
        try{
            String uriWithCommonParam = url + (url.contains("?") ? "&" : "?") + "authorizer_access_token=" + accessToken + "&component_appid=" + componentAppid;
            response = executable.execute(uriWithCommonParam, request, t);
        }catch (ByteDanceErrorException e){
            ByteDanceError error = e.getError();
            if((shouldExpireAccessToken(error))){
                // 强制设置access token过期，这样在下一次请求里就会刷新access token
                Lock lock = this.getByteDanceMiniProgramConfig().getAccessTokenLock();
                lock.lock();
                try {
                    if(StringUtils.equals(getByteDanceMiniProgramConfig().getAccessToken(), accessToken)){
                        getByteDanceMiniProgramConfig().expireAccessToken();
                    }
                }catch (Exception ex){
                    this.getByteDanceMiniProgramConfig().expireAccessToken();
                }finally {
                    lock.unlock();
                }
            }
            if (error.getErrno() != null && error.getErrno() != 0) {
                log.error("\n【请求地址】: {}\n【错误信息】：{}", url, error);
                throw new ByteDanceErrorException(error, e);
            }
        }catch (Exception e) {
            log.error("\n【请求地址】: {}\n【异常信息】：{}", url, e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * 是否让本地存储的accessToken过期
     * 当字节跳动系统返回accessToken相关错误时，应该让本地存储的accessToken过期，不再可用
     * @param error
     * @return
     */
    private boolean shouldExpireAccessToken(ByteDanceError error){
        return ByteDanceMiniProgramErrorMsgEnum.CODE_40020.getCode() == error.getErrno()
            ||ByteDanceMiniProgramErrorMsgEnum.CODE_40021.getCode() == error.getErrno();
    }

    /**
     * 是否应该进行请求重试
     * 当字节跳动系统响应系统错误或者accessToken失效时，应该重试
     * @param error
     * @return
     */
    @Override
    public boolean shouldRetry(ByteDanceError error){
        return shouldExpireAccessToken(error)
            || ByteDanceMiniProgramErrorMsgEnum.CODE_40000.getCode() == error.getErrno();
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
