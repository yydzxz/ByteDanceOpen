package com.github.yydzxz.open.api.v1;

import com.github.yydzxz.open.api.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.v1.response.auth.AuthAppListResponse;
import com.github.yydzxz.open.api.v1.response.auth.AuthRetrieveResponse;
import com.github.yydzxz.open.api.v1.response.auth.GetAuthorizerAccessTokenResponse;

/**
 * 第三方平台业务
 * 具体包括授权，模版管理，上传图片材料，代授权小程序业务
 * @author yangyidian
 */
public interface IByteDanceOpenV1ComponentService extends IByteDanceOpenComponentService {

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

    String AUTH_APP_LIST_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/auth_app_list";

    /**
     * 找回授权码
     */
    String AUTH_RETRIEVE = "https://open.microapp.bytedance.com/openapi/v1/auth/retrieve";

    /**
     * 获取素材管理service
     * @return
     */
    IByteDanceOpenV1MaterialService getByteDanceOpenMaterialService();

    /**
     * 获取模版管理service
     * @return
     */
    IByteDanceOpenV1TemplateService getByteDanceOpenTemplateService();

    /**
     * 获取订单下单支付管理service
     */
    IByteDanceOpenV1MiniProgramOrderPayService getByteDanceOrderPayService();

    /**
     * 获取指定appid的代授权小程序service
     * <br>用于代授权小程序业务</br>
     * @param appid
     * @return
     */
    IByteDanceOpenV1MiniProgramService getByteDanceOpenV1MiniProgramServiceByAppid(String appid);

    /**
     * 获取用户授权页URL（来路URL和成功跳转URL 的域名都需要为三方平台设置的 登录授权的发起页域名）.
     */
    String getPreAuthUrl(String redirectURI);

    /**
     * 使用授权码换取小程序的接口调用凭据.
     * @param authorizationCode
     */
    GetAuthorizerAccessTokenResponse getAuthorizerAccessTokenByAuthorizationCode(String authorizationCode);


    /**
     * 获取小程序的接口调用凭据（令牌）
     * @param appId
     * @param forceRefresh
     * @return
     */
    String getAuthorizerAccessToken(String appId, boolean forceRefresh);

    /**
     * 查询所有授权给第三方平台的小程序列表
     * @param page 页码，从1开始
     * @param size 页长，从1开始，最多为50
     * @return
     */
    AuthAppListResponse authAppList(Integer page, Integer size);

    AuthRetrieveResponse authRetrieve(String appId);

}
