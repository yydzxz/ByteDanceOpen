package com.github.yydzxz.open.api.v2;

import com.github.yydzxz.open.api.v1.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.v2.request.auth.GetPreAuthCodeRequest;

/**
 * @author yangyidian
 * @date 2020/09/09
 **/
public interface IByteDanceOpenV2ComponentService extends IByteDanceOpenComponentService {

    /**
     * 获取预授权码 pre_auth_code
     */
    String V2_API_CREATE_PRE_AUTH_CODE_URL = "https://open.microapp.bytedance.com/openapi/v2/auth/pre_auth_code";


    String getPreAuthUrl(String redirectURI, GetPreAuthCodeRequest request);
}
