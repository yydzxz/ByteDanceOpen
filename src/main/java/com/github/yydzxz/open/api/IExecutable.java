package com.github.yydzxz.open.api;

import com.google.common.collect.Multimap;

/**
 * @author yangyidian
 * @date 2020/07/09
 **/
@FunctionalInterface
public interface IExecutable<T> {

    T execute(String uriWithCommonParam, Multimap<String,String> headers, Object request, Class<T> t);
}
