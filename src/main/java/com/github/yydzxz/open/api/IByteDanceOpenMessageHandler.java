package com.github.yydzxz.open.api;

import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.Map;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
public interface IByteDanceOpenMessageHandler {

    /**
     * 处理消息
     * @param message
     * @return
     */
    ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context);

}
