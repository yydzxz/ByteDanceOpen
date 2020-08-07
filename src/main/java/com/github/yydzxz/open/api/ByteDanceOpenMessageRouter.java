package com.github.yydzxz.open.api;

import com.github.yydzxz.common.IByteDanceMessageDuplicateChecker;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.common.util.json.GsonSerializer;
import com.github.yydzxz.common.util.json.JsonSerializer;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Data
public class ByteDanceOpenMessageRouter {

    private IByteDanceRedisOps byteDanceRedisOps;

    private List<ByteDanceOpenMessageRouterRule> rules = new ArrayList<>();

    private IByteDanceMessageDuplicateChecker messageDuplicateChecker;

    private JsonSerializer jsonSerializer;

    public ByteDanceOpenMessageRouter() {
    }

    public ByteDanceOpenMessageRouter(IByteDanceMessageDuplicateChecker messageDuplicateChecker) {
        this(messageDuplicateChecker, ByteDanceJsonBuilder.instance());
    }

    public ByteDanceOpenMessageRouter(IByteDanceMessageDuplicateChecker messageDuplicateChecker, JsonSerializer jsonSerializer) {
        this.messageDuplicateChecker = messageDuplicateChecker;
        this.jsonSerializer = jsonSerializer;
    }

    /**
     * 开始一个新的Route规则.
     */
    public ByteDanceOpenMessageRouterRule rule() {
        return new ByteDanceOpenMessageRouterRule(this);
    }



    public ByteDanceOpenMessageHandleResult route(final ByteDanceOpenMessage message){
        return route(message, new HashMap<>(1));
    }

    /**
     * 匹配符合的规则，然后用这些规则中的handlers去处理消息.
     */
    public ByteDanceOpenMessageHandleResult route(final ByteDanceOpenMessage message, Map<String, Object> context){
        ByteDanceOpenMessageHandleResult result = new ByteDanceOpenMessageHandleResult();

        if (isMsgDuplicated(message)) {
            log.warn("重复消息，不做处理. {}", jsonSerializer.toJson(message));
            return result;
        }

        final List<ByteDanceOpenMessageRouterRule> matchedRules = new ArrayList<>();

        for(ByteDanceOpenMessageRouterRule rule : this.rules){
            if(rule.test(message)){
                matchedRules.add((rule));
            }
        }

        if(matchedRules.isEmpty()){
            return result;
        }

        for(final ByteDanceOpenMessageRouterRule rule : matchedRules){
            //返回最后一个handler的处理结果
            try{
                result = rule.handle(message, context);
            }catch (Exception e){
                log.error("消息处理失败，清除消息重复状态===>{}", jsonSerializer.toJson(message));
                //如果这条消息处理报错，那么清除这条消息的重复状态，这样字节服务再次推送这条消息的时候，可以再次处理
                this.messageDuplicateChecker.clearDuplicate(getMessageId(message));
                throw e;
            }
        }
        return result;
    }

    private String getMessageId(final ByteDanceOpenMessage message){
        StringBuilder sb = new StringBuilder("bytedance:event:msgid:");
        sb.append(message.getAppId() == null ? "appidempty" : message.getAppId())
            .append(":")
            .append(message.getEvent())
            .append(":")
            .append(message.getMsgType() == null ? "msgtypeempty" : message.getMsgType())
            .append(":")
            .append(message.getEventTime() == null ? "eventtimeempty" : message.getEventTime().getTime())
            .append(":")
            .append(message.getCreateTime() == null ? "creatimeempty" : message.getCreateTime().getTime());
        return sb.toString();
    }

    private boolean isMsgDuplicated(final ByteDanceOpenMessage message){
        if(this.messageDuplicateChecker == null){
            log.warn("没有配置消息重复检查器，不进行消息重复性检查");
            return false;
        }
        String messageId = getMessageId(message);
        log.info("进行消息重复性检查: {}", messageId);
        return this.messageDuplicateChecker.isDuplicate(messageId);
    }
}
