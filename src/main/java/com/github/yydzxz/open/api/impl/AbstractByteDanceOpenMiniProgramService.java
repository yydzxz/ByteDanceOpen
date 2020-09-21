package com.github.yydzxz.open.api.impl;

import cn.hutool.core.util.StrUtil;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.common.error.ByteDanceErrorMsgEnum;
import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.IExecutable;
import com.github.yydzxz.open.api.IRetryableExecutor;
import com.github.yydzxz.open.error.ByteDanceOpenMiniProgramException;
import com.google.common.collect.Multimap;
import java.util.concurrent.locks.Lock;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/09/21
 **/
@Slf4j
public abstract class AbstractByteDanceOpenMiniProgramService implements IByteDanceOpenMiniProgramService, IRetryableExecutor {

    @Getter
    private String appId;

    @Getter
    @Setter
    private long retrySleepMillis = 1000;

    @Getter
    @Setter
    private int maxRetryTimes = 5;

    public abstract IByteDanceOpenService getByteDanceOpenService();

    public abstract IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    public AbstractByteDanceOpenMiniProgramService(String appId) {
        this.appId = appId;
    }

    @Override
    public String get(String url){
        return get(url, String.class);
    }

    @Override
    public <T> T get(String url, Class<T> t){
        return retryableExecuteRequest((url2 ,headers, request2, t2)->{
            return getInternal(url2, t2);
        },url, null, null, t);
    }

    private <T> T getInternal(String url, Class<T> t){
        return executeRequest(
            (uriWithCommonParam, headers, request2, t2) -> {
                return getByteDanceOpenService().getByteDanceHttpRequestService().get(uriWithCommonParam, t2);
            },url, null, null, t
        );
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t){
        return retryableExecuteRequest((url2 , headers, request2 , t2)->{
            return postInternal(url2, request2, t2);
        },url, null, request, t);
    }

    private <T> T postInternal(String url, Object request, Class<T> t){
        return executeRequest(
            (uriWithCommonParam, headers, request2, t2) -> {
                return getByteDanceOpenService().getByteDanceHttpRequestService().post(uriWithCommonParam, request2, t2);
            },url, null, request, t
        );
    }

    @Override
    public <T> T postWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t) {
        return retryableExecuteRequest((url2 , headers2, request2 , t2)->{
            return postWithHeadersInternal(url2, headers2, request2, t2);
        },url, headers, request, t);
    }

    private <T> T postWithHeadersInternal(String url, Multimap<String, String> headers, Object request, Class<T> t){
        return executeRequest(
            (uriWithCommonParam, headers2, request2, t2) -> {
                return getByteDanceOpenService().getByteDanceHttpRequestService().post(uriWithCommonParam, request2, t2);
            },url, headers, request, t
        );
    }

    private <T> T executeRequest(IExecutable<T> executable, String url, Multimap<String,String> headers, Object request, Class<T> t){
        String accessToken = getAccessToken(false);
        String componentAppid = getByteDanceOpenConfigStorage().getComponentAppId();
        T response = null;
        try{
            String uriWithCommonParam = url + (url.contains("?") ? "&" : "?") + "authorizer_access_token=" + accessToken + "&component_appid=" + componentAppid;
            response = executable.execute(uriWithCommonParam, headers, request, t);
        }catch (ByteDanceErrorException e){
            ByteDanceError error = e.getError();
            if((shouldExpireAccessToken(error))){
                // 强制设置access token过期，这样在下一次请求里就会刷新access token
                Lock lock = getByteDanceOpenConfigStorage().getAccessTokenLock(appId);
                lock.lock();
                try {
                    if(StrUtil.equals(getAccessToken(false), accessToken)){
                        getByteDanceOpenConfigStorage().expireAuthorizerAccessToken(appId);
                    }
                }catch (Exception ex){
                    getByteDanceOpenConfigStorage().expireAuthorizerAccessToken(appId);
                }finally {
                    lock.unlock();
                }
            }
            if (error.getErrno() != null && error.getErrno() != 0) {
                log.error("\n【请求地址】: {}\n【错误信息】: {}", url, error);
                throw new ByteDanceOpenMiniProgramException(appId, error, e);
            }
        }catch (Exception e) {
            log.error("\n【请求地址】: {}\n【异常信息】: {}", url, e.getMessage());
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
    protected boolean shouldExpireAccessToken(ByteDanceError error){
        return ByteDanceErrorMsgEnum.CODE_40020.getCode() == error.getErrno()
            || ByteDanceErrorMsgEnum.CODE_40021.getCode() == error.getErrno();
    }
}
