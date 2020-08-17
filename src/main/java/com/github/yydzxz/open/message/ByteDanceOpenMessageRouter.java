package com.github.yydzxz.open.message;

import com.github.yydzxz.common.message.IByteDanceMessageDuplicateChecker;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.common.util.json.JsonSerializer;
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



    public ByteDanceOpenMessageResult route(final ByteDanceOpenMessage message){
        return route(message, new HashMap<>(1));
    }

    /**
     * 匹配符合的规则，然后用这些规则中的handlers去处理消息.
     */
    public ByteDanceOpenMessageResult route(final ByteDanceOpenMessage message, Map<String, Object> context){

        ByteDanceOpenMessageResult messageResult = new ByteDanceOpenMessageResult();

        final List<ByteDanceOpenMessageRouterRule> matchedRules = new ArrayList<>();

        for(ByteDanceOpenMessageRouterRule rule : this.rules){
            if(rule.test(message)){
                matchedRules.add((rule));
            }
        }

        if(matchedRules.isEmpty()){
            return messageResult;
        }

        ByteDanceOpenMessageRouterRuleResult ruleResult;
        for(final ByteDanceOpenMessageRouterRule rule : matchedRules){
            ruleResult = rule.handle(message, context);
            messageResult.addRouterRuleResult(ruleResult);
        }
        return messageResult;
    }
}
