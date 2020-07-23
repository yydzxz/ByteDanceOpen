package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramInfoService;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;
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
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/07/02
 **/
@Slf4j
public class ByteDanceOpenMiniProgramInfoServiceImpl implements IByteDanceOpenMiniProgramInfoService {

    private IByteDanceOpenMiniProgramService byteDanceOpenMiniProgramService;

    public ByteDanceOpenMiniProgramInfoServiceImpl(IByteDanceOpenMiniProgramService byteDanceOpenMiniProgramService) {
        this.byteDanceOpenMiniProgramService = byteDanceOpenMiniProgramService;
    }

    @Override
    public AppInfoResponse getAppInfo() {
        return byteDanceOpenMiniProgramService.get(APP_INFO_URL, AppInfoResponse.class);
    }

    @Override
    public byte[] getAppQrCode(AppQrCodeRequest request) {
        return byteDanceOpenMiniProgramService.post(APP_QRCODE_URL, request, byte[].class);
    }

    @Override
    public AppCheckAppNameResponse checkAppName(String appName) {
        String url = APP_CHECK_APP_NAME_URL + "?app_name=" + appName;
        return byteDanceOpenMiniProgramService.get(url, AppCheckAppNameResponse.class);
    }

    @Override
    public AppModifyAppNameResponse modifyAppName(AppModifyAppNameRequest request) {
        return byteDanceOpenMiniProgramService.post(APP_MODIFY_APP_NAME_URL, request, AppModifyAppNameResponse.class);
    }

    @Override
    public AppModifyAppIntroResponse modifyAppIntro(AppModifyAppIntroRequest request) {
        return byteDanceOpenMiniProgramService.post(APP_MODIFY_APP_INTRO_URL, request, AppModifyAppIntroResponse.class);
    }

    @Override
    public AppModifyAppIconResponse modifyAppIcon(AppModifyAppIconRequest request) {
        return byteDanceOpenMiniProgramService.post(APP_MODIFY_APP_ICON_URL, request, AppModifyAppIconResponse.class);
    }

    @Override
    public AppModifyServerDomainResponse modifyServerDomain(AppModifyServerDomainRequest request){
        return byteDanceOpenMiniProgramService.post(APP_MODIFY_SERVER_DOMAIN_URL, request, AppModifyServerDomainResponse.class);
    }

    @Override
    public AppModifyWebviewDomainResponse modifyWebviewDomain(AppModifyWebviewDomainRequest request){
        return byteDanceOpenMiniProgramService.post(APP_MODIFY_WEBVIEW_DOMAIN_URL, request, AppModifyWebviewDomainResponse.class);
    }
}
