package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
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
    private IByteDanceOpenComponentService byteDanceOpenComponentService;

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
