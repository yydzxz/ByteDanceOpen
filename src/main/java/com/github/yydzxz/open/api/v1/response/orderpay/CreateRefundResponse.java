package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreateRefundResponse  extends  OrderPayBaseResponse{

    /**
     * 担保交易服务端退款单号
     */
    @JSONField(name = "refund_no")
    @JsonAlias("refund_no")
    @JsonProperty("refund_no")
    @SerializedName("refund_no")
    private String refundNo;
}
