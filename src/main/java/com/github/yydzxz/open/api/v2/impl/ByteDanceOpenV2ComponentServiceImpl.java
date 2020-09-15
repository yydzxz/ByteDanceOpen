package com.github.yydzxz.open.api.v2.impl;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.impl.ByteDanceOpenComponentServiceImpl;
import com.github.yydzxz.open.api.v1.response.auth.GetPreAuthCodeResponse;
import com.github.yydzxz.open.api.v2.IByteDanceOpenV2ComponentService;
import com.github.yydzxz.open.api.v2.request.auth.GetPreAuthCodeRequest;
import com.github.yydzxz.open.util.URIUtil;

/**
 * @author yangyidian
 * @date 2020/09/09
 **/
public class ByteDanceOpenV2ComponentServiceImpl extends ByteDanceOpenComponentServiceImpl implements IByteDanceOpenV2ComponentService {

    public ByteDanceOpenV2ComponentServiceImpl(IByteDanceOpenService byteDanceOpenService) {
        super(byteDanceOpenService);
    }

    @Override
    public String getPreAuthUrl(String redirectURI){
        return getPreAuthUrl(redirectURI, null);
    }

    @Override
    public String getPreAuthUrl(String redirectURI, GetPreAuthCodeRequest request) {
        GetPreAuthCodeResponse response = post(V2_API_CREATE_PRE_AUTH_CODE_URL,request, GetPreAuthCodeResponse.class);
        StringBuilder preAuthUrl = new StringBuilder(String.format(COMPONENT_LOGIN_PAGE_URL,
            getByteDanceOpenConfigStorage().getComponentAppId(),
            response.getPreAuthCode(),
            URIUtil.encodeURIComponent(redirectURI)));
        String preAuthUrlStr = preAuthUrl.toString();
        return preAuthUrlStr;
    }
}
