package com.github.yydzxz.open.message.handler;

import com.github.yydzxz.open.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.message.ByteDanceOpenMessageHandleResult;
import com.github.yydzxz.open.message.IByteDanceOpenMessageHandler;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 授权事件处理器
 * @author yangyidian
 * @date 2020/07/10
 **/
@Slf4j
public class AuthorizedEventHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        log.info("AuthorizedEventHandler handle message");
        return null;
    }
}
