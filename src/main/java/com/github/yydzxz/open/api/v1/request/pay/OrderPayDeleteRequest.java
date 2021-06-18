package com.github.yydzxz.open.api.v1.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/06/18
 **/
@Data
public class OrderPayDeleteRequest implements IByteDanceRequest {

    /**
     * 做订单展示的字节系 app 名称
     * douyin 抖音
     */
    private String appname;

    /**
     * 小程序用户 id
     */
    private String openid;

    @JSONField(name = "order_id")
    @JsonAlias("order_id")
    @JsonProperty("order_id")
    @SerializedName("order_id")
    private String orderId;
}
