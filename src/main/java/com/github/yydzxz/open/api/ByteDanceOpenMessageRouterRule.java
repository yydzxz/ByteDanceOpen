package com.github.yydzxz.open.api;

import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件由哪些handler进行处理
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Data
public class ByteDanceOpenMessageRouterRule {

    private ByteDanceOpenMessageRouter router;

    public ByteDanceOpenMessageRouterRule(ByteDanceOpenMessageRouter router) {
        this.router = router;
    }

    private String event;

    private String msgType;

    private List<IByteDanceOpenMessageHandler> handlers = new ArrayList<>();

    public ByteDanceOpenMessageRouterRule event(String event){
        this.event = event;
        return this;
    }

    public ByteDanceOpenMessageRouterRule msgType(String msgType){
        this.msgType = msgType;
        return this;
    }

    /**
     * 设置消息处理器
     */
    public ByteDanceOpenMessageRouterRule addHandler(IByteDanceOpenMessageHandler handler) {
        this.handlers.add(handler);
        return this;
    }


    public ByteDanceOpenMessageRouter end(){
        this.router.getRules().add(this);
        return this.router;
    }

    /**
     * 使用该规则对应的所有handlers处理消息
     * @return
     */
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context){
        ByteDanceOpenMessageHandleResult result = null;
        for(IByteDanceOpenMessageHandler handler : this.handlers){
            if(handler == null){
                log.warn("处理event:[{}],msgType:[{}]的handler为null", event, msgType);
            }else{
                result = handler.handle(message, context);
            }
        }
        // 返回最后handler的结果
        return result;
    }


    /**
     * 测试事件与规则是否匹配
     */
    protected boolean test(ByteDanceOpenMessage message) {
        return isEventMatch(message) && isMsgTypeMatch(message);
    }

    private boolean isEventMatch(ByteDanceOpenMessage message){
        return event == null || event.equalsIgnoreCase(message.getEvent());
    }

    private boolean isMsgTypeMatch(ByteDanceOpenMessage message){
        return msgType == null || msgType.equalsIgnoreCase(message.getMsgType());
    }


}
