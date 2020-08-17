package com.github.yydzxz.open.message;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * 每个rule里的handlers处理完message后的结果
 *
 * @author yangyidian
 * @date 2020/08/17
 **/
@Data
public class ByteDanceOpenMessageRouterRuleResult {
    private Map<IByteDanceOpenMessageHandler, ByteDanceOpenMessageHandleResult> results = new HashMap<>();

    private Map<IByteDanceOpenMessageHandler, Exception> exceptions = new HashMap<>();

    public Map<IByteDanceOpenMessageHandler, ByteDanceOpenMessageHandleResult> addResult(IByteDanceOpenMessageHandler handler, ByteDanceOpenMessageHandleResult result){
        results.put(handler, result);
        return results;
    }

    public Map<IByteDanceOpenMessageHandler, Exception> addException(IByteDanceOpenMessageHandler handler, Exception e){
        exceptions.put(handler, e);
        return exceptions;
    }
}
