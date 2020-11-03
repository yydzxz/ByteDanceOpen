package com.github.yydzxz.open.api.v2.impl;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.impl.AbstractByteDanceOpenComponentService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1ComponentService;
import com.github.yydzxz.open.api.v2.IByteDanceOpenV2ComponentService;
import com.github.yydzxz.open.api.v2.IByteDanceOpenV2MiniProgramService;
import com.github.yydzxz.open.api.v2.request.auth.GetPreAuthCodeRequest;
import com.github.yydzxz.open.api.v2.response.auth.GetPreAuthCodeResponse;
import com.github.yydzxz.open.util.URIUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/09/09
 **/
@Slf4j
public class ByteDanceOpenV2ComponentServiceImpl extends AbstractByteDanceOpenComponentService implements IByteDanceOpenV2ComponentService {

    private static final Map<String, IByteDanceOpenV2MiniProgramService> BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP = new ConcurrentHashMap<>();

    public ByteDanceOpenV2ComponentServiceImpl(IByteDanceOpenService byteDanceOpenService, IByteDanceOpenV1ComponentService byteDanceOpenV1ComponentService) {
        super(byteDanceOpenService);
        //目前为了简便，getComponentAccessToken,getAuthorizerAccessToken等是直接使用v1版本的，所以这里构造函数中需要IByteDanceOpenV1ComponentService
        //当以后字节有了这些接口的v2版本,使用v2重写后，这里就可以不需要IByteDanceOpenV1ComponentService了
        byteDanceOpenService.setByteDanceOpenV1ComponentService(byteDanceOpenV1ComponentService);
    }

    @Override
    public IByteDanceOpenV2MiniProgramService getByteDanceOpenV2MiniProgramServiceByAppid(String appId) {
        IByteDanceOpenV2MiniProgramService byteDanceOpenMiniProgramService = BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.get(appId);
        if (byteDanceOpenMiniProgramService == null) {
            synchronized (BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP) {
                byteDanceOpenMiniProgramService = BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.get(appId);
                if (byteDanceOpenMiniProgramService == null) {
                    byteDanceOpenMiniProgramService = new ByteDanceOpenV2MiniProgramServiceImpl(this, appId);
                    BYTEDANCE_OPEN_MINI_PROGRAM_SERVICE_MAP.put(appId, byteDanceOpenMiniProgramService);
                }
            }
        }
        return byteDanceOpenMiniProgramService;
    }

    /**
     * 直接使用v1的实现
     * @param forceRefresh
     * @return

     */
    @Override
    public String getComponentAccessToken(boolean forceRefresh) {
        return getByteDanceOpenService().getByteDanceOpenV1ComponentService().getComponentAccessToken(forceRefresh);
    }

    /**
     * 直接使用v1的实现
     * @param appId
     * @param forceRefresh
     * @return
     */
    @Override
    public String getAuthorizerAccessToken(String appId, boolean forceRefresh){
        return getByteDanceOpenService().getByteDanceOpenV1ComponentService().getAuthorizerAccessToken(appId, forceRefresh);
    }

    @Override
    public String getPreAuthUrl(String redirectURI, GetPreAuthCodeRequest request) {
        GetPreAuthCodeResponse response = post(API_CREATE_PRE_AUTH_CODE_URL,request, GetPreAuthCodeResponse.class);
        StringBuilder preAuthUrl = new StringBuilder(String.format(COMPONENT_LOGIN_PAGE_URL,
            getByteDanceOpenService().getByteDanceOpenConfigStorage().getComponentAppId(),
            response.getPreAuthCode(),
            URIUtil.encodeURIComponent(redirectURI)));
        return preAuthUrl.toString();
    }

}
