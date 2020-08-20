package com.github.yydzxz.common.service.impl;

import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.common.util.json.JsonSerializer;
import com.google.common.collect.Multimap;
import java.util.Map;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/08/10
 **/
@Slf4j
public class JoddHttpByteDanceHttpRequestServiceImpl extends AbstractByteDanceHttpRequestService {

    public JoddHttpByteDanceHttpRequestServiceImpl() {
        super(ByteDanceJsonBuilder.instance());
    }

    public JoddHttpByteDanceHttpRequestServiceImpl(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    @Override
    <T> T doGet(String url, Class<T> clazz) {
        HttpResponse response = HttpRequest.get(url)
            .contentTypeJson()
            .acceptJson()
            .send();
        if(clazz == byte[].class){
            return (T)response.bodyBytes();
        }else {
            return getJsonSerializer().parse(response.bodyText(), clazz);
        }
    }

    @Override
    <T> T doPost(String url, Object request, Class<T> clazz) {
        HttpResponse response = HttpRequest.post(url)
            .contentTypeJson()
            .acceptJson()
            .bodyText(getJsonSerializer().toJson(request))
            .send();
        if(clazz == byte[].class){
            return (T)response.bodyBytes();
        }else{
            return getJsonSerializer().parse(response.bodyText(), clazz);
        }
    }

    @Override
    <T> T doPostWithHeaders(String url, Multimap<String, String> headers, Object requestParam, Class<T> clazz) {
        HttpRequest httpRequest = HttpRequest.post(url);
        Map<String,String> headersMap = multimapHeaders2MapHeaders(headers);
        if(headersMap.get("Content-Type") != null && headersMap.get("Content-Type").contains("form-data")){
            Map<String, Object> paramsMap = (Map)handlerRequestParam(requestParam);
            httpRequest = httpRequest.form(paramsMap);
        }else {
            httpRequest = httpRequest.contentTypeJson()
                .acceptJson()
                .bodyText(getJsonSerializer().toJson(requestParam));
        }
        HttpResponse response = httpRequest
            .header(multimapHeaders2MapHeaders(headers))
            .send();
        if(clazz == byte[].class){
            return (T)response.bodyBytes();
        }else{
            return getJsonSerializer().parse(response.bodyText(), clazz);
        }
    }
}
