package com.github.yydzxz.open.api.request.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class GetPreAuthCodeRequest {

    @JSONField(name = "component_appid")
    @JsonAlias("component_appid")
    private String componentAppid;

    @JSONField(name = "component_access_token")
    @JsonAlias("component_access_token")
    private String componentAccessToken;
}
