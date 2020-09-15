package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.IByteDanceOpenConfigStorage;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.github.yydzxz.open.api.v2.IByteDanceOpenV2ComponentService;
import lombok.Data;
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

    @Setter
    private IByteDanceOpenComponentService byteDanceOpenComponentService;

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

    @Override
    public IByteDanceOpenComponentService getByteDanceOpenComponentService() {
        if(byteDanceOpenV2ComponentService != null){
            return byteDanceOpenV2ComponentService;
        }else if(byteDanceOpenComponentService != null){
            return byteDanceOpenComponentService;
        }else {
            throw new RuntimeException("IByteDanceOpenService 尚未设置可用的 IByteDanceOpenComponentService 或者 IByteDanceOpenV2ComponentService(推荐)");
        }
    }
}
