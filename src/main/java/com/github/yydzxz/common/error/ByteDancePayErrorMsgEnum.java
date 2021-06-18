package com.github.yydzxz.common.error;

import lombok.Getter;

/**
 * fuck
 *
 * @author yangyidian
 * @date 2021/06/18
 **/
@Getter
public enum  ByteDancePayErrorMsgEnum {

    CODE_NEGATIVE_1(-1, "系统错误"),

    CODE_40002(40002, "access_token 错误");

    private int code;
    private String msg;

    ByteDancePayErrorMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
