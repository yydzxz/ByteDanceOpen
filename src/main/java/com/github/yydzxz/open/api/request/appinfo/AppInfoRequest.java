package com.github.yydzxz.open.api.request.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class AppInfoRequest {
    @JSONField(name = "component_appid")
    @JsonAlias("component_appid")
    private String componentAppid;

    @JSONField(name = "authorizer_access_token")
    @JsonAlias("authorizer_access_token")
    private String authorizerAccessToken;
}
