package com.github.yydzxz.open.error;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.error.ByteDanceErrorException;

/**
 * @author yangyidian
 * @date 2020/07/29
 **/
public class ByteDanceOpenMiniProgramException extends ByteDanceErrorException {

    private String appid;

    public ByteDanceOpenMiniProgramException(String appid,ByteDanceError error) {
        super(error);
        this.appid = appid;
    }

    public ByteDanceOpenMiniProgramException(String appid,ByteDanceError error, Throwable cause) {
        super(error, cause);
        this.appid = appid;
    }

    public String getAppid() {
        return appid;
    }
}
