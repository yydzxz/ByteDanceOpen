package com.github.yydzxz.open.error;

import com.github.yydzxz.common.error.ByteDanceError;

/**
 * @author yangyidian
 * @date 2020/07/29
 **/
public class NonAuthorizedAppException extends ByteDanceOpenMiniProgramException {

    private String appid;

    public NonAuthorizedAppException(String appid, ByteDanceError error) {
        super(appid, error);
    }

    public NonAuthorizedAppException(String appid, ByteDanceError error, Throwable cause) {
        super(appid, error, cause);
    }
}
