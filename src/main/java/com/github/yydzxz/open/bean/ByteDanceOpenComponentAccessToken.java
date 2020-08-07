package com.github.yydzxz.open.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceOpenComponentAccessToken implements Serializable {

    private static final long serialVersionUID = 6417155856835777760L;

    @JsonProperty("component_access_token")
    @JSONField(name = "component_access_token")
    @SerializedName("component_access_token")
    private String componentAccessToken;

    @JsonProperty("expires_in")
    @JSONField(name = "expires_in")
    @SerializedName("expires_in")
    private int expiresIn = -1;

    public static ByteDanceOpenComponentAccessToken fromJson(String json) {
        return ByteDanceJsonBuilder.instance().parse(json, ByteDanceOpenComponentAccessToken.class);
    }
}
