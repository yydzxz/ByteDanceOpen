//package com.yyd.open.api.impl;
//
//import cn.hutool.json.JSONUtil;
//import com.cecd.sdk.AppProperties;
//import com.yyd.common.redis.RedissonByteDanceRedisOps;
//import com.yyd.open.api.IByteDanceOpenComponentService;
//import com.yyd.open.api.IByteDanceOpenConfigStorage;
//import com.yyd.open.api.IByteDanceOpenService;
//import javax.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @author yangyidian
// * @date 2020/06/19
// **/
//@Slf4j
//@Service
//public class ByteDanceOpenServiceImpl implements IByteDanceOpenService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private IByteDanceOpenComponentService byteDanceOpenComponentService;
//
//    private IByteDanceOpenConfigStorage byteDanceOpenConfigStorage;
//
//    @Autowired
//    private RedissonByteDanceRedisOps redissonByteDanceRedisOps;
//
//    @Autowired
//    private ByteDanceOpenProperties byteDanceOpenProperties;
//
//    @Autowired
//    private AppProperties appProperties;
//
//    @PostConstruct
//    public void init() {
//        byteDanceOpenComponentService = new ByteDanceOpenComponentServiceImpl(this);
//
//        ByteDanceOpenInRedisConfigStorage inRedisConfigStorage = new ByteDanceOpenInRedisConfigStorage(redissonByteDanceRedisOps, appProperties.getRedisPrefix());
//        inRedisConfigStorage.setComponentAppId(byteDanceOpenProperties.getComponentAppId());
//        inRedisConfigStorage.setComponentAppSecret(byteDanceOpenProperties.getComponentSecret());
//        inRedisConfigStorage.setComponentToken(byteDanceOpenProperties.getComponentToken());
//        inRedisConfigStorage.setComponentAesKey(byteDanceOpenProperties.getComponentAesKey());
//        setByteDanceOpenConfigStorage(inRedisConfigStorage);
//    }
//    @Override
//    public IByteDanceOpenComponentService getByteDanceOpenComponentService() {
//        return byteDanceOpenComponentService;
//    }
//
//    @Override
//    public IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage() {
//        return byteDanceOpenConfigStorage;
//    }
//
//    @Override
//    public void setByteDanceOpenConfigStorage(IByteDanceOpenConfigStorage openConfigStorage) {
//        this.byteDanceOpenConfigStorage = openConfigStorage;
//    }
//
//    @Override
//    public <T> T get(String url, Class<T> t){
//        log.info("get请求字节跳动接口,请求地址: [{}]",url);
//        T response = restTemplate.getForObject(url, t);
//        if(response instanceof String){
//            checkError((String)response);
//        }
//        return response;
//    }
//
//    @Override
//    public <T> T post(String url, Object request, Class<T> t){
//        log.info("post请求字节跳动接口, 请求地址[{}], 参数[{}]",url, JSONUtil.toJsonStr(request));
//        T response = restTemplate.postForObject(url, request, t);
//        if(response instanceof String){
//            checkError((String)response);
//        }
//        return response;
//    }
//
//    private void checkError(String response){
//        log.debug(response);
//        ByteDanceError error;
//        try{
//            error = JSONUtil.toBean(response, ByteDanceError.class);
//        }catch (Exception e){
//            log.error("字节跳动接口未知错误");
//            log.error(e.getMessage(), e);
//            error = new ByteDanceError();
//            error.setErrno(ByteDanceMiniProgramErrorMsgEnum.CODE_40000.getCode());
//            error.setMessage("字节跳动接口未知错误");
//            throw new ByteDanceErrorException(error);
//        }
//        if(error.getErrno() != null && error.getErrno() != 0){
//            log.error("字节跳动接口返回内容: {}", response);
//            throw new ByteDanceErrorException(error);
//        }
//    }
//}
