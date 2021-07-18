package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryRefundResponse  extends  OrderPayBaseResponse{
    /**
     * 订单退款基本信息
     */
    private RefundInfo refundInfo;

    @Data
    public static class RefundInfo{
        /**
         * 担保支付侧的退款单号
         */
        @JSONField(name = "refund_no")
        @JsonAlias("refund_no")
        @JsonProperty("refund_no")
        @SerializedName("refund_no")
        private String refundNo;

        /**
         * 退款金额，单位[分]
         */
        @JSONField(name = "refund_amount")
        @JsonAlias("refund_amount")
        @JsonProperty("refund_amount")
        @SerializedName("refund_amount")
        private BigDecimal refundAmount;

        /**
         * 退款状态，成功-SUCCESS；失败-FAIL
         */
        @JSONField(name = "refund_status")
        @JsonAlias("refund_status")
        @JsonProperty("refund_status")
        @SerializedName("refund_status")
        private String refundStatus;
    }
}
