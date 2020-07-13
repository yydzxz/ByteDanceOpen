package com.github.yydzxz.open.api;

/**
 * @author yangyidian
 * @date 2020/07/09
 **/
@FunctionalInterface
public interface IExecutable<T> {

    T execute(String uriWithCommonParam, Object request, Class<T> t);
}
