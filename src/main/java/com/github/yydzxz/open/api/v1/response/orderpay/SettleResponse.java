package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SettleResponse  extends  OrderPayBaseResponse{

    /**
     * 平台生成分账单号
     */
    @JSONField(name = "settle_no")
    @JsonAlias("settle_no")
    @JsonProperty("settle_no")
    @SerializedName("settle_no")
    private String settleNo;
}
