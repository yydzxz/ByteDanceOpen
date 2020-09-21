package com.github.yydzxz.open.message;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/08/14
 **/
@Data
public class ByteDanceOpenMessageResult {

    private Map<IByteDanceOpenMessageHandler, ByteDanceOpenMessageHandleResult> results = new HashMap<>();

    private Map<IByteDanceOpenMessageHandler, Exception> exceptions = new HashMap<>();

    public void addRouterRuleResult(ByteDanceOpenMessageRouterRuleResult routerRuleResult){
        addResults(routerRuleResult.getResults());
        addExceptions(routerRuleResult.getExceptions());
    }

    /**
     * 如果所有handler都正常处理，也就是exceptions.isEmpty()为true，那么返回success，否则返回failed
     * @return
     */
    public String getDefaultResult(){
        if(exceptions.isEmpty()){
            return "success";
        }else {
            return "failed";
        }
    }

    private Map<IByteDanceOpenMessageHandler, ByteDanceOpenMessageHandleResult> addResults(Map<IByteDanceOpenMessageHandler, ByteDanceOpenMessageHandleResult> newResults){
        results.putAll(newResults);
        return results;
    }

    private Map<IByteDanceOpenMessageHandler, Exception> addExceptions(Map<IByteDanceOpenMessageHandler, Exception> newExceptions){
        exceptions.putAll(newExceptions);
        return exceptions;
    }
}
