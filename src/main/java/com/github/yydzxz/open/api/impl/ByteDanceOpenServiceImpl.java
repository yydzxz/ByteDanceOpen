package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1ComponentService;
import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.github.yydzxz.open.api.v2.IByteDanceOpenV2ComponentService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
@Slf4j
public class ByteDanceOpenServiceImpl implements IByteDanceOpenService {

    @Getter
    @Setter
    private IByteDanceHttpRequestService byteDanceHttpRequestService;

    @Getter
    @Setter
    private IByteDanceOpenV1ComponentService byteDanceOpenV1ComponentService;

    @Getter
    @Setter
    private IByteDanceOpenV2ComponentService byteDanceOpenV2ComponentService;

    @Getter
    @Setter
    private IByteDanceOpenConfigStorage byteDanceOpenConfigStorage;

    @Getter
    @Setter
    private RedissonClient redissonClient;

    @Getter
    @Setter
    private IByteDanceRedisOps byteDanceRedisOps;
}
