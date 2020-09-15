package com.github.yydzxz.open.api;

import com.google.common.collect.Multimap;

/**
 * @author yangyidian
 * @date 2020/07/09
 **/
@FunctionalInterface
public interface IExecutable<T> {

    /**
     *
     * @param url 请求地址
     * @param headers 请求头
     * @param request 请求参数
     * @param t 返回值类型
     * @return
     */
    T execute(String url, Multimap<String,String> headers, Object request, Class<T> t);
}
