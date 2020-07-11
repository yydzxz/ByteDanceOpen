package com.github.yydzxz.open.api;


import com.github.yydzxz.common.service.IByteDanceHttpRequestService;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
public interface IByteDanceOpenService{
    IByteDanceOpenComponentService getByteDanceOpenComponentService();

    IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    void setByteDanceOpenConfigStorage(IByteDanceOpenConfigStorage openConfigStorage);

    IByteDanceHttpRequestService getByteDanceHttpRequestService();
}
