package com.github.yydzxz.common.service.impl;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.common.util.json.FastJsonSerializer;
import com.github.yydzxz.common.util.json.JsonSerializer;
import com.google.common.collect.Multimap;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
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
public class RestTemplateByteDanceHttpRequestServiceImpl extends AbstractByteDanceHttpRequestService {

    private RestTemplate restTemplate;

    public RestTemplateByteDanceHttpRequestServiceImpl() {
        super(ByteDanceJsonBuilder.instance());
        this.restTemplate = new RestTemplate();
    }

    public RestTemplateByteDanceHttpRequestServiceImpl(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
        this.restTemplate = new RestTemplate();
        if(jsonSerializer instanceof FastJsonSerializer){
            this.restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        }
    }

    public RestTemplateByteDanceHttpRequestServiceImpl(RestTemplate restTemplate) {
        super(ByteDanceJsonBuilder.instance());
        this.restTemplate = restTemplate;
    }

    public RestTemplateByteDanceHttpRequestServiceImpl(RestTemplate restTemplate, JsonSerializer jsonSerializer) {
        super(jsonSerializer);
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T doGet(String url, Class<T> t) {
        return restTemplate.getForObject(url, t);
    }

    @Override
    public <T> T doPost(String url, Object request, Class<T> t) {
        return restTemplate.postForObject(url, request, t);
    }

    @Override
    public <T> T doPostWithHeaders(String url, Multimap<String,String> headers, Object request, Class<T> t){
        HttpHeaders httpHeaders = new HttpHeaders();
        for(String headerName: headers.keySet()){
            Collection<String> headerValues = headers.get(headerName);
            for(String headerValue : headerValues){
                httpHeaders.add(headerName, headerValue);
            }
        }
        MultiValueMap<String, Object> param = (MultiValueMap)handlerRequestParam(request);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(param, httpHeaders), t);
        return responseEntity.getBody();
    }

    @Override
    protected Object handlerRequestParam(Object requestParams){
        MultiValueMap<String, Object> paramsMap = new LinkedMultiValueMap<>();
        Field[] fields = requestParams.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(requestParams);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if(value != null && value instanceof File){
                value = new FileSystemResource((File)value);
            }
            String aliasName = getJsonSerializer().getFieldAliasName(field);
            paramsMap.add(aliasName, value);
        }
        return paramsMap;
    }
}
