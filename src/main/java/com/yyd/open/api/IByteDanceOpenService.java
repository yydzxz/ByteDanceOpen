package com.yyd.open.api;


import com.yyd.common.service.IByteDanceHttpRequestService;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
public interface IByteDanceOpenService extends IByteDanceHttpRequestService {
    IByteDanceOpenComponentService getByteDanceOpenComponentService();

    IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    void setByteDanceOpenConfigStorage(IByteDanceOpenConfigStorage openConfigStorage);
}
