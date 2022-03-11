package com.github.yydzxz.open.api;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;

/**
 * @author yangyidian
 * @date 2020/07/10
 **/
public interface IRetryableExecutor {
    /**
     * <pre>
     * 重试间隔时间
     * 默认：1000ms
     * </pre>
     *
     */
    default long getRetrySleepMillis(){
        return 1000;
    }

    /**
     * <pre>
     * 重试最大次数最大重试次数.
     * 默认：5次
     * </pre>
     *
     */
    default int getMaxRetryTimes(){
        return 5;
    }

    /**
     * 通过retryableExecuteRequest执行请求捕获到ByteDanceErrorException时，是否进行重试
     * @param error 根据error判断是否应该重试
     * @return
     */
    default boolean shouldRetry(ByteDanceError error){
        return false;
    }

    Logger getLogger();

    /**
     * 带有重试功能的请求
     * @param executable
     * @param url
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    default <T> T retryableExecuteRequest(IExecutable<T> executable, String url, Multimap<String,String> headers, Object request, Class<T> t){
        int retryTimes = 1;
        ByteDanceErrorException exception = null;
        T response;
        while(true){
            if(retryTimes > getMaxRetryTimes()){
                if(getLogger() != null){
                    getLogger().error("重试达到最大次数【{}】", retryTimes - 1);
                }
                throw exception;
            }
            try {
                response = executable.execute(url, headers, request, t);
                break;
            }catch (ByteDanceErrorException e){
                ByteDanceError error = e.getError();
                if(shouldRetry(error)){
                    if(getLogger() != null){
                        getLogger().warn("字节跳动接口请求失败，{} ms 后重试(第{}次)", getRetrySleepMillis(), retryTimes + 1);
                    }
                    retryTimes += 1;
                    exception = e;
                    try {
                        Thread.sleep(getRetrySleepMillis());
                    } catch (InterruptedException ex) {
                        getLogger().error(ex.getMessage(), ex);
                    }
                }else {
                    throw e;
                }
            }
        }
        return response;
    }

}
