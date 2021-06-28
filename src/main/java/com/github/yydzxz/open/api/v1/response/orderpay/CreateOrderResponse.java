package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreateOrderResponse  extends  OrderPayBaseResponse{
    /**
     * 拉起收银台的 orderInfo
     */
    private OrderInfo data;

    @Data
    public static class OrderInfo{

        @JSONField(name = "order_id")
        @JsonAlias("order_id")
        @JsonProperty("order_id")
        @SerializedName("order_id")
        private String orderId;

        @JSONField(name = "order_token")
        @JsonAlias("order_token")
        @JsonProperty("order_token")
        @SerializedName("order_token")
        private String orderToken;
    }
}
