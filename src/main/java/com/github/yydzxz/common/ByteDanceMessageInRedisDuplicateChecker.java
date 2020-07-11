package com.github.yydzxz.common;

import cn.hutool.core.date.DateUtil;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

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
        boolean notExist = StringUtils.isEmpty(byteDanceRedisOps.getValue(messageId));
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
