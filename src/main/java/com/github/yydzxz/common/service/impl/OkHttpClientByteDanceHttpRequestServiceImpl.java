package com.github.yydzxz.common.service.impl;

import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.common.util.json.JsonSerializer;
import com.google.common.collect.Multimap;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author yangyidian
 * @date 2020/08/03
 **/
@Slf4j
public class OkHttpClientByteDanceHttpRequestServiceImpl extends AbstractByteDanceHttpRequestService {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public OkHttpClient client;

    private JsonSerializer jsonSerializer;

    public OkHttpClientByteDanceHttpRequestServiceImpl() {
        this.client = new OkHttpClient();
        this.jsonSerializer = ByteDanceJsonBuilder.instance();
    }

    public OkHttpClientByteDanceHttpRequestServiceImpl(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
        this.client = new OkHttpClient();
    }

    public OkHttpClientByteDanceHttpRequestServiceImpl(OkHttpClient client) {
        this.client = client;
        this.jsonSerializer = ByteDanceJsonBuilder.instance();
    }

    public OkHttpClientByteDanceHttpRequestServiceImpl(OkHttpClient client, JsonSerializer jsonSerializer) {
        this.client = client;
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public JsonSerializer getJsonSerializer() {
        return jsonSerializer;
    }

    @Override
    public <T> T doGet(String url, Class<T> clazz) {
        Request request = new Request.Builder()
            .url(url)
            .build();
        return doRequest(request, clazz);
    }

    @Override
    public <T> T doPostWithHeaders(String url, Multimap<String, String> headers, Object requestParam, Class<T> clazz) {
        Headers.Builder headersBuilder = Headers.of().newBuilder();
        for(String headerName: headers.keySet()){
            Collection<String> headerValues = headers.get(headerName);
            for(String headerValue : headerValues){
                headersBuilder.add(headerName, headerValue);
            }
        }

        Map<String, Object> paramsMap = (Map)handlerRequestParam(requestParam);
        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();

        if(headers.get("Content-Type") != null && headers.get("Content-Type").contains("multipart/form-data")){
            requestBodyBuilder = requestBodyBuilder.setType(MultipartBody.FORM);
        }

        for(String paramName : paramsMap.keySet()){
            if(paramsMap.get(paramName) instanceof File){
                File file = (File)paramsMap.get(paramName);
                requestBodyBuilder = requestBodyBuilder.addFormDataPart(paramName, file.getName(), RequestBody.create(file, null));
            }else{
                requestBodyBuilder = requestBodyBuilder.addFormDataPart(paramName, (String)paramsMap.get(paramName));
            }
        }

        RequestBody body = requestBodyBuilder.build();
        Request request = new Request.Builder()
            .headers(headersBuilder.build())
            .url(url)
            .post(body)
            .build();

        return doRequest(request, clazz);
    }

    @Override
    Object handlerRequestParam(Object requestParams) {
        Field[] fields = requestParams.getClass().getDeclaredFields();
        Map<String, Object> paramsMap = new HashMap<>(fields.length);
        for(Field field : fields){
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(requestParams);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            String aliasName = getJsonSerializer().getFieldAliasName(field);
            paramsMap.put(aliasName, value);
        }
        return paramsMap;
    }

    @Override
    public <T> T doPost(String url, Object requestParam, Class<T> clazz) {
        RequestBody body = RequestBody.create(jsonSerializer.toJson(requestParam), JSON);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        return doRequest(request, clazz);
    }

    private <T> T doRequest(Request request, Class<T> clazz){
        try (Response result = client.newCall(request).execute()) {
            if(clazz == byte[].class){
                return (T)result.body().bytes();
            }else{
                String resultString = result.body().string();
                return getJsonSerializer().parse(resultString, clazz);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
