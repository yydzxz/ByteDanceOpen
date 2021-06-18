package com.github.yydzxz.open.api.v1.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/06/18
 **/
@Data
public class OrderPayUpdateRequest implements IByteDanceRequest {

    /**
     * 做订单展示的字节系 app 名称
     * douyin 抖音
     */
    private String appname;

    /**
     * 小程序用户 id
     */
    private String openid;

    /**
     * 订单更新列表，不超过 10 单
     */
    @JSONField(name = "order_update_list")
    @JsonAlias("order_update_list")
    @JsonProperty("order_update_list")
    @SerializedName("order_update_list")
    private List<Order> orderUpdateList;

    @Data
    public static class Order{
        @JSONField(name = "order_id")
        @JsonAlias("order_id")
        @JsonProperty("order_id")
        @SerializedName("order_id")
        private String orderId;

        private String status;

        @JSONField(name = "detail_url")
        @JsonAlias("detail_url")
        @JsonProperty("detail_url")
        @SerializedName("detail_url")
        private String detailUrl;

        @JSONField(name = "update_time")
        @JsonAlias("update_time")
        @JsonProperty("update_time")
        @SerializedName("update_time")
        private Long updateTime;
    }
}
