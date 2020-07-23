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
public class GetAuthorizerAccessTokenReponse extends ByteDanceError {

    private static final long serialVersionUID = -8337462028108565681L;

    @JSONField(name = "authorizer_access_token")
    @JsonAlias("authorizer_access_token")
    private String authorizerAccessToken;

    @JSONField(name = "authorizer_refresh_token")
    @JsonAlias("authorizer_refresh_token")
    private String authorizerRefreshToken;

    @JSONField(name = "authorizer_appid")
    @JsonAlias("authorizer_appid")
    private String authorizerAppid;

    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    private Integer expiresIn;
}
