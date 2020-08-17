package com.github.yydzxz.common.message;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import java.util.concurrent.TimeUnit;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
public class ByteDanceMessageInRedisDuplicateChecker implements IByteDanceMessageDuplicateChecker {

    private IByteDanceRedisOps byteDanceRedisOps;

    public ByteDanceMessageInRedisDuplicateChecker(IByteDanceRedisOps byteDanceRedisOps) {
        this.byteDanceRedisOps = byteDanceRedisOps;
    }

    @Override
    public boolean isDuplicate(String messageId) {
        boolean notExist = StrUtil.isEmpty(byteDanceRedisOps.getValue(messageId));
        if(notExist){
            byteDanceRedisOps.setValue(messageId, DateUtil.now(), 6, TimeUnit.MINUTES);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean clearDuplicate(String messageId){
        return byteDanceRedisOps.deleteKey(messageId);
    }
}
