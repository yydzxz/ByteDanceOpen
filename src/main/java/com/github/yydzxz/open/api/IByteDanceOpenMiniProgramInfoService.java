package com.github.yydzxz.open.api;


import com.github.yydzxz.open.api.request.appinfo.AppModifyAppIconRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyAppIntroRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyAppNameRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyServerDomainRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyWebviewDomainRequest;
import com.github.yydzxz.open.api.request.appinfo.AppQrCodeRequest;
import com.github.yydzxz.open.api.response.appinfo.AppCheckAppNameResponse;
import com.github.yydzxz.open.api.response.appinfo.AppInfoResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyAppIconResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyAppIntroResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyAppNameResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyServerDomainResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyWebviewDomainResponse;

/**
 * 代授权小程序业务-基本信息配置 相关API
 * @author yangyidian
 * @date 2020/07/02
 **/
public interface IByteDanceOpenMiniProgramInfoService {

    /**
     * 获取小程序基本信息
     */
    String APP_INFO_URL  = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/info";

    /**
     * 获取二维码
     */
    String APP_QRCODE_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/qrcode";

    /**
     * 小程序名称检测
     */
    String APP_CHECK_APP_NAME_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/check_app_name";

    /**
     * 修改小程序名称
     */
    String APP_MODIFY_APP_NAME_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/modify_app_name";

    /**
     * 修改小程序简介
     */
    String APP_MODIFY_APP_INTRO_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/modify_app_intro";

    /**
     * 修改小程序图标
     */
    String APP_MODIFY_APP_ICON_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/modify_app_icon";

    /**
     * 修改服务器域名
     */
    String APP_MODIFY_SERVER_DOMAIN_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/modify_server_domain";

    /**
     * 修改 webview 域名
     */
    String APP_MODIFY_WEBVIEW_DOMAIN_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/app/modify_webview_domain";

    /**
     * 获取小程序基本信息
     * @return
     */
    AppInfoResponse getAppInfo();

    /**
     * 获取二维码
     * @param request
     * @return
     */
    byte[] getAppQrCode(AppQrCodeRequest request);

    /**
     * 小程序名称检测
     * @param appName 小程序待检测名称
     * @return
     */
    AppCheckAppNameResponse checkAppName(String appName);

    /**
     * 修改小程序名称
     * @param request
     * @return
     */
    AppModifyAppNameResponse modifyAppName(AppModifyAppNameRequest request);

    /**
     * 修改小程序简介
     * @param request
     * @return
     */
    AppModifyAppIntroResponse modifyAppIntro(AppModifyAppIntroRequest request);

    /**
     * 修改小程序图标
     * @param request
     * @return
     */
    AppModifyAppIconResponse modifyAppIcon(AppModifyAppIconRequest request);

    /**
     * 修改服务器域名
     * 注意：事先需要在第三方平台（https://open.microapp.bytedance.com）设置好第三方平台的服务器域名，接口服务器域名的选项必须在第三方平台的服务器域名列表内
     * 提示：设置的服务器域名可以是第三方平台服务器域名的子域名
     * @param request
     * @return
     */
    AppModifyServerDomainResponse modifyServerDomain(AppModifyServerDomainRequest request);

    /**
     * 修改 webview 域名
     * @param request
     * @return
     */
    AppModifyWebviewDomainResponse modifyWebviewDomain(AppModifyWebviewDomainRequest request);
}
