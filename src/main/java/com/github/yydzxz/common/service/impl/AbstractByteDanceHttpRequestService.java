package com.github.yydzxz.common.service.impl;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.github.yydzxz.common.service.IByteDanceResponse;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/08/04
 **/
@Slf4j
public abstract class AbstractByteDanceHttpRequestService implements IByteDanceHttpRequestService {

    @Override
    public <T> T get(String url, Class<T> t){
        log.info("get请求字节跳动接口,请求地址: 【{}】",url);
        T response = doGet(url, t);
        if(response instanceof IByteDanceResponse){
            log.info("请求字节跳动接口返回数据: 【{}】", getJsonSerializer().toJson(response));
        }else {
            log.info("请求字节跳动接口返回数据, 类型:【{}】, 内容:【{}】", t.getTypeName(), response);

        }
        return handleResponse(response);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t){
        log.info("post请求字节跳动接口, 请求地址【{}】, 参数【{}】",url, getJsonSerializer().toJson(request));
        T response = doPost(url, request, t);
        if(response instanceof IByteDanceResponse){
            log.info("请求字节跳动接口返回数据: 【{}】", getJsonSerializer().toJson(response));
        }else {
            log.info("请求字节跳动接口返回数据, 类型:【{}】, 内容:【{}】", t.getTypeName(), response);

        }
        return handleResponse(response);
    }

    @Override
    public <T> T postWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t){
        log.info("post请求字节跳动接口, 请求地址【{}】, 参数【{}】",url, getJsonSerializer().toJson(request));
        T response = doPostWithHeaders(url, headers, request, t);
        if(response instanceof IByteDanceResponse){
            log.info("请求字节跳动接口返回数据: 【{}】", getJsonSerializer().toJson(response));
        }else {
            log.info("请求字节跳动接口返回数据, 类型:【{}】, 内容:【{}】", t.getTypeName(), response);

        }
        return response;
    }


    private <T> T handleResponse(T response){
        if(response instanceof IByteDanceResponse){
            checkError(getJsonSerializer().toJson(response));
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
        ByteDanceError error = getJsonSerializer().parse(response, ByteDanceError.class);
        if(error.getErrno() != null && error.getErrno() != 0){
            throw new ByteDanceErrorException(error);
        }
    }

    String doGet(String url){
        return doGet(url, String.class);
    }

    abstract <T> T doGet(String url, Class<T> t);


    String doPost(String url, Object obj){
        return doPost(url, obj, String.class);
    }

    abstract <T> T doPost(String url, Object request, Class<T> t);


    abstract <T> T doPostWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t);

    /**
     * 有些值要特殊处理，比如用Resttemplate的时候，File要转成FileSystemResource
     * @param requestParams
     * @return
     */
    abstract Object handlerRequestParam(Object requestParams);
}
