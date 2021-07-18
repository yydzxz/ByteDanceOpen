package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryOrderResponse  extends  OrderPayBaseResponse{

    private PaymentInfo payment_info;

    @Data
    public static class PaymentInfo{

        /**
         * total_fee : 1200
         * order_status : PROCESSING-处理中|SUCCESS-成功|FAIL-失败|TIMEOUT-超时
         * pay_time : 支付时间
         * way : 1 2-支付宝，1-微信，3-银行卡
         * channel_no : 渠道单号
         * channel_gateway_no : 渠道网关号
         */

        /**
         * 担保交易服务端退款单号
         */
        @JSONField(name = "refund_no")
        @JsonAlias("refund_no")
        @JsonProperty("refund_no")
        @SerializedName("refund_no")
        private BigDecimal totalFee;

        /**
         * 订单状态 PROCESSING-处理中|SUCCESS-成功|FAIL-失败|TIMEOUT-超时
         */
        @JSONField(name = "order_status")
        @JsonAlias("order_status")
        @JsonProperty("order_status")
        @SerializedName("order_status")
        private String order_status;

        /**
         * 支付时间
         */
        @JSONField(name = "pay_time")
        @JsonAlias("pay_time")
        @JsonProperty("pay_time")
        @SerializedName("pay_time")
        private String payTime;

        /**
         * 支付渠道：2-支付宝，1-微信，3-银行卡
         */
        private Integer way;

        /**
         * 渠道单号
         */
        @JSONField(name = "channel_no")
        @JsonAlias("channel_no")
        @JsonProperty("channel_no")
        @SerializedName("channel_no")
        private String channelNo;

        /**
         * 渠道网关号
         */
        @JSONField(name = "channel_gateway_no")
        @JsonAlias("channel_gateway_no")
        @JsonProperty("channel_gateway_no")
        @SerializedName("channel_gateway_no")
        private String channelGatewayNo;

    }
}
