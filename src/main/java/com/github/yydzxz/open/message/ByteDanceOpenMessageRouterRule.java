package com.github.yydzxz.open.message;

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
    public ByteDanceOpenMessageRouterRuleResult handle(ByteDanceOpenMessage message, Map<String, Object> context){
        ByteDanceOpenMessageRouterRuleResult ruleResult = new ByteDanceOpenMessageRouterRuleResult();
        ByteDanceOpenMessageHandleResult handlerResult = null;
        for(IByteDanceOpenMessageHandler handler : this.handlers){
            String messageId = getMessageId(handler, message);
            if(!isMessageHandledByHandler(handler, message)){
                try {
                    handlerResult = handler.handle(message, context);
                    ruleResult.addResult(handler, handlerResult);
                }catch (Exception e){
                    log.error("handler[{}]处理消息[{}]失败", handler.getHandlerName(), messageId);
                    // 如果这条消息处理报错，那么清除这条消息的重复状态，这样字节服务再次推送这条消息的时候，可以再次处理
                    log.error("消息处理失败，清除消息重复状态===>{}", router.getJsonSerializer().toJson(message));
                    log.error(e.getMessage(), e);
                    ruleResult.addException(handler, e);
                    router.getMessageDuplicateChecker().clearDuplicate(messageId);
                }
            }
        }
        return ruleResult;
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

    /**
     * 根据handler和message, 生成一个唯一的id
     * @param handler
     * @param message
     * @return
     */
    private String getMessageId(IByteDanceOpenMessageHandler handler, final ByteDanceOpenMessage message){
        StringBuilder sb = new StringBuilder("bytedance:event:msgid:");
        sb.append(message.getAppId() == null ? "appidempty" : message.getAppId())
            .append(":")
            .append(message.getEvent())
            .append(":")
            .append(message.getMsgType() == null ? "msgtypeempty" : message.getMsgType())
            .append(":")
            .append(message.getEventTime() == null ? "eventtimeempty" : message.getEventTime().getTime())
            .append(":")
            .append(message.getCreateTime() == null ? "creatimeempty" : message.getCreateTime().getTime())
            .append(":")
            .append(handler.getHandlerName());
        return sb.toString();
    }

    /**
     * message是否已经被handler处理过了
     * @param handler
     * @param message
     * @return
     */
    private boolean isMessageHandledByHandler(IByteDanceOpenMessageHandler handler, ByteDanceOpenMessage message){
        if(handler.repeatable()){
            return false;
        }
        String messageId = getMessageId(handler, message);
        if(router.getMessageDuplicateChecker() == null){
            log.warn("没有配置消息重复检查器，不进行消息重复性检查");
            return false;
        }
        log.info("进行消息重复性检查: {}", messageId);
        return router.getMessageDuplicateChecker().isDuplicate(messageId);
    }

}
