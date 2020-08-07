package com.github.yydzxz.open.api.response.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.service.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class GetPreAuthCodeResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -1208735625532808579L;

    @SerializedName("pre_auth_code")
    @JSONField(name = "pre_auth_code")
    @JsonAlias("pre_auth_code")
    private String preAuthCode;

    @SerializedName("expires_in")
    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    private String expiresIn;

}
