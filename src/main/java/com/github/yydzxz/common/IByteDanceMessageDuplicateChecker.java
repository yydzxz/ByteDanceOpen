package com.github.yydzxz.common;

/**
 * 消息重复检查器。
 * 字节第三方平台服务器发送给第三方服务的事件推送必须直接返回字符串 success，否则会进行多次重试（只重试 5 次）。每次间隔1分钟
 * @author yangyidian
 * @date 2020/07/08
 **/
public interface IByteDanceMessageDuplicateChecker {

    /**
     * 自己构造可以唯一确定一条消息的messageId
     * @param messageId
     * @return
     */
    boolean isDuplicate(String messageId);
}
