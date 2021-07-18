package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class QueryRefundRequest  extends OrderPayBaseRequest {
    /**
     * 商户分配退款号
     */
    @JSONField(name = "out_refund_no")
    @JsonAlias("out_refund_no")
    @JsonProperty("out_refund_no")
    @SerializedName("out_refund_no")
    private String outRefundNo;
}
