package com.github.yydzxz.common.service.impl;

import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.common.util.json.JsonSerializer;
import com.google.common.collect.Multimap;
import java.io.File;
import java.io.IOException;
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
 * https://square.github.io/okhttp/
 * @author yangyidian
 * @date 2020/08/03
 **/
@Slf4j
public class OkHttpClientByteDanceHttpRequestServiceImpl extends AbstractByteDanceHttpRequestService {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public OkHttpClient client;

    public OkHttpClientByteDanceHttpRequestServiceImpl() {
        super(ByteDanceJsonBuilder.instance());
        this.client = new OkHttpClient();
    }

    public OkHttpClientByteDanceHttpRequestServiceImpl(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
        this.client = new OkHttpClient();
    }

    public OkHttpClientByteDanceHttpRequestServiceImpl(OkHttpClient client) {
        super(ByteDanceJsonBuilder.instance());
        this.client = client;
    }

    public OkHttpClientByteDanceHttpRequestServiceImpl(OkHttpClient client, JsonSerializer jsonSerializer) {
        super(jsonSerializer);
        this.client = client;
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
        Map<String, String> headersMap = multimapHeaders2MapHeaders(headers);
        Headers.Builder headersBuilder = Headers.of().newBuilder();
        for(String headerName: headersMap.keySet()){
            String headerValue = headersMap.get(headerName);
            headersBuilder.add(headerName, headerValue);
        }
        Map<String, Object> paramsMap = (Map)handlerRequestParam(requestParam);
        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();

        if(headersMap.get("Content-Type") != null && headersMap.get("Content-Type").contains("form-data")){
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
    public <T> T doPost(String url, Object requestParam, Class<T> clazz) {
        RequestBody body = RequestBody.create(getJsonSerializer().toJson(requestParam), JSON);
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
