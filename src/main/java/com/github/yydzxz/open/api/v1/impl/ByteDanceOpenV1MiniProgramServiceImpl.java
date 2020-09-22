package com.github.yydzxz.open.api.v1.impl;

import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.impl.AbstractByteDanceOpenMiniProgramService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1ComponentService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramCodeService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramInfoService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @author yangyidian
 * @date 2020/06/24
 **/
@Slf4j
public class ByteDanceOpenV1MiniProgramServiceImpl extends AbstractByteDanceOpenMiniProgramService implements IByteDanceOpenV1MiniProgramService {

    private IByteDanceOpenV1ComponentService byteDanceOpenV1ComponentService;

    private IByteDanceOpenV1MiniProgramCodeService byteDanceOpenV1MiniProgramCodeService;

    private IByteDanceOpenV1MiniProgramInfoService byteDanceOpenMiniProgramInfoService;

    public ByteDanceOpenV1MiniProgramServiceImpl(IByteDanceOpenV1ComponentService byteDanceOpenV1ComponentService, String appId) {
        super(appId);
        this.byteDanceOpenV1ComponentService = byteDanceOpenV1ComponentService;
        this.byteDanceOpenV1MiniProgramCodeService = new ByteDanceOpenV1MiniProgramCodeServiceImpl(this);
        this.byteDanceOpenMiniProgramInfoService = new ByteDanceOpenV1MiniProgramInfoServiceImpl(this);
    }

    @Override
    public IByteDanceOpenV1ComponentService getByteDanceOpenV1ComponentService(){
        return byteDanceOpenV1ComponentService;
    }

    @Override
    public String getAccessToken() {
        return getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean forceRefresh) {
        return getByteDanceOpenV1ComponentService().getAuthorizerAccessToken(getAppId(), forceRefresh);
    }

    @Override
    public IByteDanceOpenService getByteDanceOpenService(){
        return byteDanceOpenV1ComponentService.getByteDanceOpenService();
    }

    @Override
    public IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage() {
        return byteDanceOpenV1ComponentService.getByteDanceOpenService().getByteDanceOpenConfigStorage();
    }

    @Override
    public IByteDanceOpenV1MiniProgramCodeService getByteDanceOpenMiniProgramCodeService() {
        return byteDanceOpenV1MiniProgramCodeService;
    }

    @Override
    public IByteDanceOpenV1MiniProgramInfoService getByteDanceOpenMiniProgramInfoService() {
        return byteDanceOpenMiniProgramInfoService;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
