package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AddMerchantRequest  extends OrderPayBaseRequest {

    /**
     * 授权码兑换接口调用凭证
     */
    @JSONField(name = "component_access_token")
    @JsonAlias("component_access_token")
    @JsonProperty("component_access_token")
    @SerializedName("component_access_token")
    private String componentAccessToken;

    /**
     * 小程序第三方平台应用 id
     */
    @JSONField(name = "thirdparty_component_id")
    @JsonAlias("thirdparty_component_id")
    @JsonProperty("thirdparty_component_id")
    @SerializedName("thirdparty_component_id")
    private String thirdpartyComponentId;

    /**
     * 链接类型：1-进件页面 2-账户余额页
     */
    @JSONField(name = "url_type")
    @JsonAlias("url_type")
    @JsonProperty("url_type")
    @SerializedName("url_type")
    private String urlType;
}
