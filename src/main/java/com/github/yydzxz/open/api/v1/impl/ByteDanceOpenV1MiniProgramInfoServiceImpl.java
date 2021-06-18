package com.github.yydzxz.open.api.v1.impl;

import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramInfoService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramService;
import com.github.yydzxz.open.api.v1.request.appinfo.*;
import com.github.yydzxz.open.api.v1.response.appinfo.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/07/02
 **/
@Slf4j
public class ByteDanceOpenV1MiniProgramInfoServiceImpl implements IByteDanceOpenV1MiniProgramInfoService {

    private IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService;

    public ByteDanceOpenV1MiniProgramInfoServiceImpl(IByteDanceOpenV1MiniProgramService byteDanceOpenMiniProgramService) {
        this.byteDanceOpenV1MiniProgramService = byteDanceOpenMiniProgramService;
    }

    @Override
    public Code2SessionResponse code2Session(String code,String anonymousCode) {
        String url = String.format(CODE_SESSION_URL+"?code=%s&anonymous_code=%s", code, anonymousCode);
        return byteDanceOpenV1MiniProgramService.get(url, Code2SessionResponse.class);
    }

    @Override
    public AppInfoResponse getAppInfo() {
        return byteDanceOpenV1MiniProgramService.get(APP_INFO_URL, AppInfoResponse.class);
    }

    @Override
    public byte[] appsQrcode(AppsQrcodeRequest request) {
        return byteDanceOpenV1MiniProgramService.post(APPS_QRCODE_URL, request, byte[].class);
    }

    @Override
    public byte[] getAppQrCode(AppQrCodeRequest request) {
        return byteDanceOpenV1MiniProgramService.post(APP_QRCODE_URL, request, byte[].class);
    }

    @Override
    public AppCheckAppNameResponse checkAppName(String appName) {
        String url = APP_CHECK_APP_NAME_URL + "?app_name=" + appName;
        return byteDanceOpenV1MiniProgramService.get(url, AppCheckAppNameResponse.class);
    }

    @Override
    public AppModifyAppNameResponse modifyAppName(AppModifyAppNameRequest request) {
        return byteDanceOpenV1MiniProgramService.post(APP_MODIFY_APP_NAME_URL, request, AppModifyAppNameResponse.class);
    }

    @Override
    public AppModifyAppIntroResponse modifyAppIntro(AppModifyAppIntroRequest request) {
        return byteDanceOpenV1MiniProgramService.post(APP_MODIFY_APP_INTRO_URL, request, AppModifyAppIntroResponse.class);
    }

    @Override
    public AppModifyAppIconResponse modifyAppIcon(AppModifyAppIconRequest request) {
        return byteDanceOpenV1MiniProgramService.post(APP_MODIFY_APP_ICON_URL, request, AppModifyAppIconResponse.class);
    }

    @Override
    public AppModifyServerDomainResponse modifyServerDomain(AppModifyServerDomainRequest request){
        return byteDanceOpenV1MiniProgramService.post(APP_MODIFY_SERVER_DOMAIN_URL, request, AppModifyServerDomainResponse.class);
    }

    @Override
    public AppModifyWebviewDomainResponse modifyWebviewDomain(AppModifyWebviewDomainRequest request){
        return byteDanceOpenV1MiniProgramService.post(APP_MODIFY_WEBVIEW_DOMAIN_URL, request, AppModifyWebviewDomainResponse.class);
    }

    @Override
    public AppQualityRatingResponse qualityRating() {
        return byteDanceOpenV1MiniProgramService.get(APP_QUALITY_RATING_URL, AppQualityRatingResponse.class);
    }

    @Override
    public AppCreditScoreResponse creditScore() {
        return byteDanceOpenV1MiniProgramService.get(APP_CREDIT_SCORE_URL, AppCreditScoreResponse.class);
    }
}
