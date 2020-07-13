package com.github.yydzxz.common.service;

/**
 * @author yangyidian
 * @date 2020/07/10
 **/
public interface IByteDanceHttpRequestService {

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有API中的GET请求.
     *
     * @param url 请求接口地址
     * @return 接口响应字符串
     */
    default String get(String url){
        return get(url, String.class);
    }

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有API中的GET请求.
     * @param url
     * @param t 返回对象的类型
     * @return
     */
    <T> T get(String url, Class<T> t);


    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有API中的POST请求.
     *
     * @param url 请求接口地址
     * @param obj 请求对象
     * @return 接口响应字符串
     */
    default String post(String url, Object obj){
        return post(url, obj, String.class);
    }

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有API中的POST请求.
     * @param url
     * @param request
     * @param t 返回对象的类型
     * @return
     */
    <T> T post(String url, Object request, Class<T> t);
}
