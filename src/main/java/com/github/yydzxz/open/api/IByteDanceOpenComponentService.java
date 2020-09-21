package com.github.yydzxz.open.api;

import com.google.common.collect.Multimap;

/**
 * @author yangyidian
 * @date 2020/09/18
 **/
public interface IByteDanceOpenComponentService {

    IByteDanceOpenService getByteDanceOpenService();

//    IByteDanceOpenConfigStorage getByteDanceOpenConfigStorage();

    String getComponentAccessToken(boolean forceRefresh);

    String get(String url);

    <T> T get(String url, Class<T> t);

    <T> T post(String url, Object request, Class<T> t);

    <T> T postWithHeaders(String url, Multimap<String,String> headers, Object request, Class<T> t);
}
