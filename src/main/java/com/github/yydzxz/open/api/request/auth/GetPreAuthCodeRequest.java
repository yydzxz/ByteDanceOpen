package com.github.yydzxz.open.api.request.auth;

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
public class GetPreAuthCodeRequest implements IByteDanceRequest {

    @JSONField(name = "component_appid")
    @JsonAlias("component_appid")
    @JsonProperty("component_appid")
    @SerializedName("component_appid")
    private String componentAppid;

    @JSONField(name = "component_access_token")
    @JsonAlias("component_access_token")
    @JsonProperty("component_access_token")
    @SerializedName("component_access_token")
    private String componentAccessToken;
}
