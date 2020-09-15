package com.github.yydzxz.open.api;


import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenComponentService;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
public interface IByteDanceOpenService{


    IByteDanceOpenComponentService getByteDanceOpenComponentService();

    void setByteDanceOpenComponentService(IByteDanceOpenComponentService byteDanceOpenComponentService);

    IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    void setByteDanceOpenConfigStorage(IByteDanceOpenConfigStorage openConfigStorage);

    IByteDanceHttpRequestService getByteDanceHttpRequestService();

    void setByteDanceHttpRequestService(IByteDanceHttpRequestService byteDanceHttpRequestService);

    IByteDanceRedisOps getByteDanceRedisOps();

    void setByteDanceRedisOps(IByteDanceRedisOps byteDanceRedisOps);
}
