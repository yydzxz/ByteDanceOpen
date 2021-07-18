package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class QuerySettleRequest  extends OrderPayBaseRequest {
    /**
     * 开发者侧的分账号
     */
    @JSONField(name = "out_settle_no")
    @JsonAlias("out_settle_no")
    @JsonProperty("out_settle_no")
    @SerializedName("out_settle_no")
    private String outSettleNo;
}
