package com.github.yydzxz.open.api;

import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.github.yydzxz.open.api.response.auth.GetAuthorizerAccessTokenReponse;

/**
 * 第三方平台业务
 * 具体包括授权，模版管理，上传图片材料，代授权小程序业务
 * @author yangyidian
 */
public interface IByteDanceOpenComponentService extends IByteDanceHttpRequestService {

    /**
     * 获取第三方平台 component_access_token
     */
    String API_COMPONENT_TOKEN_URL = "https://open.microapp.bytedance.com/openapi/v1/auth/tp/token";

    /**
     * 获取预授权码 pre_auth_code
     */
    String API_CREATE_PRE_AUTH_CODE_URL = "https://open.microapp.bytedance.com/openapi/v1/create/tp/pre_auth_code";


    /**
     * 使用授权码换取小程序的接口调用凭据
     */
    String API_GET_OAUTH_TOKEN_URL = "https://open.microapp.bytedance.com/openapi/v1/oauth/token";

    /**
     * 引导小程序管理员对第三方平台进行授权
     */
    String COMPONENT_LOGIN_PAGE_URL = "https://open.microapp.bytedance.com/mappconsole/tp/authorization?component_appid=%s&pre_auth_code=%s&redirect_uri=%s";


    IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    IByteDanceOpenService getByteDanceOpenService();

    IByteDanceOpenConfigStorage getOpenConfigStorage();

    /**
     * 获取素材管理service
     * @return
     */
    IByteDanceOpenMaterialService getByteDanceOpenMaterialService();


    /**
     * 获取模版管理service
     * @return
     */
    IByteDanceOpenTemplateService getByteDanceOpenTemplateService();

    /**
     * 获取指定appid的代授权小程序service
     * <br>用于代授权小程序业务</br>
     * @param appid
     * @return
     */
    IByteDanceOpenMiniProgramService getOpenMiniProgramServiceByAppid(String appid);

    /**
     * 验签
     * @param msgSignature
     * @param tpToken
     * @param timestamp
     * @param nonce
     * @param encrypt
     * @return
     */
    boolean checkSignature(String msgSignature, String tpToken, String timestamp, String nonce, String encrypt);


    /**
     * 获取用户授权页URL（来路URL和成功跳转URL 的域名都需要为三方平台设置的 登录授权的发起页域名）.
     */
    String getPreAuthUrl(String redirectUri);

    /**
     * 使用授权码换取小程序的接口调用凭据.
     * @param authorizationCode
     */
    GetAuthorizerAccessTokenReponse getAuthorizerAccessTokenByAuthorizationCode(String authorizationCode);


    /**
     * 获取小程序的接口调用凭据（令牌）
     * @param appId
     * @param forceRefresh
     * @return
     */
    String getAuthorizerAccessToken(String appId, boolean forceRefresh);

    /**
     * 获取第三方平台 component_access_token
     * 第三方平台 component_access_token 是第三方平台的接口调用凭据，也叫做令牌
     * @param forceRefresh
     * @return
     */
    String getComponentAccessToken(boolean forceRefresh);
}
