package com.github.yydzxz.open.api.request.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.service.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class AppInfoRequest implements IByteDanceRequest {
    @JSONField(name = "component_appid")
    @JsonAlias("component_appid")
    @JsonProperty("component_appid")
    @SerializedName("componentAppid")
    private String componentAppid;

    @JSONField(name = "authorizer_access_token")
    @JsonAlias("authorizer_access_token")
    @JsonProperty("authorizer_access_token")
    @SerializedName("authorizer_access_token")
    private String authorizerAccessToken;
}
