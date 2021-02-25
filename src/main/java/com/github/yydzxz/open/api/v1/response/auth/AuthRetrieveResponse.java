package com.github.yydzxz.open.api.v1.response.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/02/25
 **/
@Data
public class AuthRetrieveResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = 7613353315595508981L;

    @SerializedName("authorization_code")
    @JSONField(name = "authorization_code")
    @JsonAlias("authorization_code")
    @JsonProperty("authorization_code")
    private String authorizationCode;

    @SerializedName("expires_in")
    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    @JsonProperty("expires_in")
    private Integer expiresIn;
}
