package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class QueryOrderRequest extends OrderPayBaseRequest {

    /**
     * 开发者侧的订单号, 同一小程序下不可重复
     */
    @JSONField(name = "out_order_no")
    @JsonAlias("out_order_no")
    @JsonProperty("out_order_no")
    @SerializedName("out_order_no")
    private String outOrderNo;
}
