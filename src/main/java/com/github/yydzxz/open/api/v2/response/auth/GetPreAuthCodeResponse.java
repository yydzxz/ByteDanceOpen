package com.github.yydzxz.open.api.v2.response.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.service.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/09/18
 **/
@Data
public class GetPreAuthCodeResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -5695101998619986998L;

    @SerializedName("pre_auth_code")
    @JSONField(name = "pre_auth_code")
    @JsonAlias("pre_auth_code")
    @JsonProperty("pre_auth_code")
    private String preAuthCode;

    @SerializedName("expires_in")
    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    @JsonProperty("expires_in")
    private String expiresIn;
}
