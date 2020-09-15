package com.github.yydzxz.open.api.v1.response.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.service.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class GetAuthorizerAccessTokenResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -8337462028108565681L;

    @SerializedName("authorizer_access_token")
    @JSONField(name = "authorizer_access_token")
    @JsonAlias("authorizer_access_token")
    @JsonProperty("authorizer_access_token")
    private String authorizerAccessToken;

    @SerializedName("authorizer_refresh_token")
    @JSONField(name = "authorizer_refresh_token")
    @JsonAlias("authorizer_refresh_token")
    @JsonProperty("authorizer_refresh_token")
    private String authorizerRefreshToken;

    @SerializedName("authorizer_appid")
    @JSONField(name = "authorizer_appid")
    @JsonAlias("authorizer_appid")
    @JsonProperty("authorizer_appid")
    private String authorizerAppid;

    @SerializedName("expires_in")
    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    @JsonProperty("expires_in")
    private Integer expiresIn;

    @SerializedName("authorize_permission")
    @JSONField(name = "authorize_permission")
    @JsonAlias("authorize_permission")
    @JsonProperty("authorize_permission")
    private List<AuthorizePermission> authorizePermission;


    @Data
    public static class AuthorizePermission implements Serializable {

        private static final long serialVersionUID = -6632734082518827404L;

        private Integer id;

        private String category;

        private String description;
    }
}
