package com.github.yydzxz.open.api;

import com.google.common.collect.Multimap;

/**
 * @author yangyidian
 * @date 2020/09/18
 **/
public interface IByteDanceOpenComponentService {

    IByteDanceOpenService getByteDanceOpenService();

    /**
     * 获取第三方平台 component_access_token
     * 第三方平台 component_access_token 是第三方平台的接口调用凭据，也叫做令牌
     * @param forceRefresh
     * @return
     */
    String getComponentAccessToken(boolean forceRefresh);

    String get(String url);

    <T> T get(String url, Class<T> t);

    <T> T post(String url, Object request, Class<T> t);

    <T> T postWithHeaders(String url, Multimap<String,String> headers, Object request, Class<T> t);
}
