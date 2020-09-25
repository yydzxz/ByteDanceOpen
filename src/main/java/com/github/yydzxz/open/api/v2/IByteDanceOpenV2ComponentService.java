package com.github.yydzxz.open.api.v2;

import com.github.yydzxz.open.api.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.v2.request.auth.GetPreAuthCodeRequest;

/**
 * @author yangyidian
 * @date 2020/09/18
 **/
public interface IByteDanceOpenV2ComponentService extends IByteDanceOpenComponentService {
    /**
     * 引导小程序管理员对第三方平台进行授权
     */
    String COMPONENT_LOGIN_PAGE_URL = "https://open.microapp.bytedance.com/mappconsole/tp/authorization?component_appid=%s&pre_auth_code=%s&redirect_uri=%s";

    String API_CREATE_PRE_AUTH_CODE_URL = "https://open.microapp.bytedance.com/openapi/v2/auth/pre_auth_code";

    /**
     * v2 获取用户授权页URL
     * @param redirectURI
     * @param request com.github.yydzxz.open.api.v2.request.auth.GetPreAuthCodeRequest
     * @return
     */
    String getPreAuthUrl(String redirectURI, GetPreAuthCodeRequest request);

    IByteDanceOpenV2MiniProgramService getByteDanceOpenV2MiniProgramServiceByAppid(String appId);

    String getAuthorizerAccessToken(String appId, boolean forceRefresh);
}
