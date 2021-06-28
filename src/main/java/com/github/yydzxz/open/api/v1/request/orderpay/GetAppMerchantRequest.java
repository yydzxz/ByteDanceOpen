package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GetAppMerchantRequest extends OrderPayBaseRequest {

    /**
     * 小程序的 app_id
     */
    @JSONField(name = "app_id")
    @JsonAlias("app_id")
    @JsonProperty("app_id")
    @SerializedName("app_id")
    private String appId;

    /**
     * 商户 id，用于接入方自行标识并管理进件方。由服务商自行分配管理
     */
    @JSONField(name = "sub_merchant_id")
    @JsonAlias("sub_merchant_id")
    @JsonProperty("sub_merchant_id")
    @SerializedName("sub_merchant_id")
    private String subMerchantId;

    /**
     * 链接类型：1-进件页面 2-账户余额页
     */
    @JSONField(name = "url_type")
    @JsonAlias("url_type")
    @JsonProperty("url_type")
    @SerializedName("url_type")
    private Integer urlType;
}
