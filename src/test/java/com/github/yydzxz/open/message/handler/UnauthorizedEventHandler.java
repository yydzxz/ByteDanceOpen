package com.github.yydzxz.open.message.handler;

import com.github.yydzxz.open.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.message.ByteDanceOpenMessageHandleResult;
import com.github.yydzxz.open.message.IByteDanceOpenMessageHandler;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 取消授权事件处理器
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
public class UnauthorizedEventHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        log.info("UnauthorizedEventHandler handle message");
        return null;
    }
}
