package com.github.yydzxz.common.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.google.common.collect.Multimap;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    @Override
    public <T> T postWithHeaders(String url, Multimap<String,String> headers, Object request, Class<T> t){
        HttpHeaders httpHeaders = new HttpHeaders();
        for(String headerName: headers.keySet()){
            Collection<String> headerValues = headers.get(headerName);
            for(String headerValue : headerValues){
                httpHeaders.add(headerName, headerValue);
            }
        }
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();

        Field[] fields = request.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(request);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if(value != null && value instanceof File){
                value = new FileSystemResource((File)value);
            }
            String paramName;
            Annotation filedAliasAnnotation = field.getAnnotation(JSONField.class);
            if(filedAliasAnnotation != null){
                JSONField jsonField = (JSONField)filedAliasAnnotation;
                paramName = jsonField.name();
                if(!StringUtils.isEmpty(paramName)){
                    param.add(paramName, value);
                    continue;
                }

            }

            filedAliasAnnotation = field.getAnnotation(JsonAlias.class);
            if(filedAliasAnnotation != null){
                JsonAlias jsonField = (JsonAlias)filedAliasAnnotation;
                paramName = jsonField.value()[0];
                if(!StringUtils.isEmpty(paramName)){
                    param.add(paramName, value);
                    continue;
                }
            }

            filedAliasAnnotation = field.getAnnotation(JsonProperty.class);
            if(filedAliasAnnotation != null){
                JsonProperty jsonField = (JsonProperty)filedAliasAnnotation;
                paramName = jsonField.value();
                if(!StringUtils.isEmpty(paramName)){
                    param.add(paramName, value);
                    continue;
                }
            }
            param.add(field.getName(), value);
        }

        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(param, httpHeaders), t);
        T response = responseEntity.getBody();
        return handleResponse(response);
    }

    private <T> T handleResponse(T response){
        if(response instanceof String){
            log.info("请求字节跳动接口返回数据: {}", response);
            checkError((String)response);
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
        ByteDanceError error = JSONUtil.toBean(response, ByteDanceError.class);
        if(error.getErrno() != null && error.getErrno() != 0){
            throw new ByteDanceErrorException(error);
        }
    }
}
