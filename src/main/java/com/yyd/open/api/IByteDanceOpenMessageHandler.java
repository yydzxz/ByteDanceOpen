package com.yyd.open.api;

import com.yyd.open.bean.message.ByteDanceOpenMessage;
import com.yyd.open.bean.message.ByteDanceOpenMessageHandleResult;

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
    ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message);

}
