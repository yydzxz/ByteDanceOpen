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
public class GetAuthorizerAccessTokenReponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -8337462028108565681L;

    @SerializedName("authorizer_access_token")
    @JSONField(name = "authorizer_access_token")
    @JsonAlias("authorizer_access_token")
    private String authorizerAccessToken;

    @SerializedName("authorizer_refresh_token")
    @JSONField(name = "authorizer_refresh_token")
    @JsonAlias("authorizer_refresh_token")
    private String authorizerRefreshToken;

    @SerializedName("authorizer_appid")
    @JSONField(name = "authorizer_appid")
    @JsonAlias("authorizer_appid")
    private String authorizerAppid;

    @SerializedName("expires_in")
    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    private Integer expiresIn;
}
