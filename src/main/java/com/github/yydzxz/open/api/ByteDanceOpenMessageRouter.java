package com.github.yydzxz.open.api;

import cn.hutool.json.JSONUtil;
import com.github.yydzxz.common.IByteDanceMessageDuplicateChecker;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
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

    public ByteDanceOpenMessageRouter() {
    }

    public ByteDanceOpenMessageRouter(IByteDanceMessageDuplicateChecker messageDuplicateChecker) {
        this.messageDuplicateChecker = messageDuplicateChecker;
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
            log.warn("重复消息，不做处理. {}", JSONUtil.toJsonStr(message));
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
            result = rule.handle(message, context);
        }
        return result;
    }

    private boolean isMsgDuplicated(final ByteDanceOpenMessage message){
        if(this.messageDuplicateChecker == null){
            log.warn("没有配置消息重复检查器，不进行消息重复性检查");
            return false;
        }
        StringBuilder messageId = new StringBuilder("bytedance:event:msgid:");
        messageId.append(message.getAppId() == null ? "appidempty" : message.getAppId())
            .append(":")
            .append(message.getEvent())
            .append(":")
            .append(message.getMsgType() == null ? "msgtypeempty" : message.getMsgType())
            .append(":")
            .append(message.getEventTime() == null ? "eventtimeempty" : message.getEventTime().getTime())
            .append(":")
            .append(message.getCreateTime() == null ? "creatimeempty" : message.getCreateTime().getTime());
        log.info("进行消息重复性检查: {}", messageId.toString());
        return this.messageDuplicateChecker.isDuplicate(messageId.toString());
    }
}
