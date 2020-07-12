package com.yyd.open.api.impl;

import com.yyd.common.redis.IByteDanceRedisOps;
import com.yyd.common.service.IByteDanceHttpRequestService;
import com.yyd.open.api.IByteDanceOpenComponentService;
import com.yyd.open.api.IByteDanceOpenConfigStorage;
import com.yyd.open.api.IByteDanceOpenService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
@Slf4j
public class ByteDanceOpenServiceImpl implements IByteDanceOpenService {

    private IByteDanceHttpRequestService byteDanceHttpRequestService;

    private IByteDanceOpenComponentService byteDanceOpenComponentService;

    private IByteDanceOpenConfigStorage byteDanceOpenConfigStorage;

    private RedissonClient redissonClient;

    private IByteDanceRedisOps redissonByteDanceRedisOps;

    @Override
    public IByteDanceHttpRequestService getByteDanceHttpRequestService() {
        return byteDanceHttpRequestService;
    }

    public void setByteDanceHttpRequestService(IByteDanceHttpRequestService byteDanceHttpRequestService) {
        this.byteDanceHttpRequestService = byteDanceHttpRequestService;
    }

    public void setByteDanceOpenComponentService(IByteDanceOpenComponentService byteDanceOpenComponentService) {
        this.byteDanceOpenComponentService = byteDanceOpenComponentService;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public IByteDanceRedisOps getRedissonByteDanceRedisOps() {
        return redissonByteDanceRedisOps;
    }

    public void setRedissonByteDanceRedisOps(IByteDanceRedisOps redissonByteDanceRedisOps) {
        this.redissonByteDanceRedisOps = redissonByteDanceRedisOps;
    }

    @Override
    public IByteDanceOpenComponentService getByteDanceOpenComponentService() {
        return byteDanceOpenComponentService;
    }

    @Override
    public IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage() {
        return byteDanceOpenConfigStorage;
    }

    @Override
    public void setByteDanceOpenConfigStorage(IByteDanceOpenConfigStorage openConfigStorage) {
        this.byteDanceOpenConfigStorage = openConfigStorage;
    }

}
