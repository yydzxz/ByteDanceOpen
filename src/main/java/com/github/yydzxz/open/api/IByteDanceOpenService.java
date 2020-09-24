package com.github.yydzxz.open.api;


import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.http.IByteDanceHttpRequestClient;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1ComponentService;
import com.github.yydzxz.open.api.v2.IByteDanceOpenV2ComponentService;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
public interface IByteDanceOpenService{


    IByteDanceOpenV1ComponentService getByteDanceOpenV1ComponentService();

    void setByteDanceOpenV1ComponentService(IByteDanceOpenV1ComponentService byteDanceOpenComponentService);

    IByteDanceOpenV2ComponentService getByteDanceOpenV2ComponentService();

    void setByteDanceOpenV2ComponentService(IByteDanceOpenV2ComponentService byteDanceOpenComponentService);

    IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    void setByteDanceOpenConfigStorage(IByteDanceOpenConfigStorage openConfigStorage);

    IByteDanceHttpRequestClient getByteDanceHttpRequestService();

    void setByteDanceHttpRequestService(IByteDanceHttpRequestClient byteDanceHttpRequestService);

    IByteDanceRedisOps getByteDanceRedisOps();

    void setByteDanceRedisOps(IByteDanceRedisOps byteDanceRedisOps);
}
