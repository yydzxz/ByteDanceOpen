package com.github.yydzxz.common.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.common.error.ByteDanceMiniProgramErrorMsgEnum;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateByteDanceHttpRequestServiceImpl
 *
 * @author Clevo
 * @date 2020/7/12
 */
@Slf4j
public class RestTemplateByteDanceHttpRequestServiceImpl implements IByteDanceHttpRequestService {

    private RestTemplate restTemplate;

    public RestTemplateByteDanceHttpRequestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T get(String url, Class<T> t) {
        log.info("get请求字节跳动接口,请求地址: [{}]",url);
        T response = restTemplate.getForObject(url, t);
        return handleResponse(response);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t) {
        log.info("post请求字节跳动接口, 请求地址[{}], 参数[{}]",url, JSONUtil.toJsonStr(request));
        T response = restTemplate.postForObject(url, request, t);
        return handleResponse(response);
    }

    private <T> T handleResponse(T response){
        if(response instanceof String){
            checkError((String)response);
            log.info("请求字节跳动接口返回数据: {}", response);
        }else if(response instanceof byte[]){
            try{
                checkError(new String((byte[])response));
            }catch (RuntimeException e){
                if(e instanceof ByteDanceErrorException){
                    throw e;
                }else{
                    // do nothing
                }
            }
        }
        return response;
    }

    private void checkError(String response){
        log.debug(response);
        ByteDanceError error;
        try{
            error = JSONUtil.toBean(response, ByteDanceError.class);
        }catch (RuntimeException e){
            log.error("字节跳动接口未知错误");
            log.error(e.getMessage(), e);
            throw e;
        }
        if(error.getErrno() != null && error.getErrno() != 0){
            log.error("字节跳动接口返回内容: {}", response);
            throw new ByteDanceErrorException(error);
        }
    }
}
