package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AddSubMerchantResponse  extends  OrderPayBaseResponse{

    /**
     * 请求页面链接
     */
    private String url;

    /**
     * 小程序平台分配商户号，用于后续分账标识分账方
     */
    @JSONField(name = "merchant_id")
    @JsonAlias("merchant_id")
    @JsonProperty("merchant_id")
    @SerializedName("merchant_id")
    private String merchantId;
}
