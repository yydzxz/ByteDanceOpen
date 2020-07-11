package com.github.yydzxz.open.api.response.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.yydzxz.common.error.ByteDanceError;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class GetPreAuthCodeResponse extends ByteDanceError {

    @JSONField(name = "pre_auth_code")
    @JsonAlias("pre_auth_code")
    private String preAuthCode;

    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    private String expiresIn;

}
