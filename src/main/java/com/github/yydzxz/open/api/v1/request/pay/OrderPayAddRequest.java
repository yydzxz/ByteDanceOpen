package com.github.yydzxz.open.api.v1.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/06/12
 **/
@Data
public class OrderPayAddRequest implements IByteDanceRequest {

    /**
     * 做订单展示的字节系 app 名称
     * douyin 抖音
     */
    String appname;

    /**
     * 小程序用户 id
     */
    String openid;

    /**
     * 订单列表
     */
    @JSONField(name = "order_list")
    @JsonAlias("order_list")
    @JsonProperty("order_list")
    @SerializedName("order_list")
    List<Order> orderList;

    @Data
    public static class Order{

        /**
         * 订单 id
         */
        @JSONField(name = "order_id")
        @JsonAlias("order_id")
        @JsonProperty("order_id")
        @SerializedName("order_id")
        private String orderId;

        /**
         * 订单创建的时间，10 位的 UNIX 时间戳
         */
        @JSONField(name = "create_time")
        @JsonAlias("create_time")
        @JsonProperty("create_time")
        @SerializedName("create_time")
        private Long createTime;

        /**
         * 订单状态更新的时间(这里指的是订单完成支付的时间)，10 位的 UNIX 时间戳
         */
        @JSONField(name = "update_time")
        @JsonAlias("update_time")
        @JsonProperty("update_time")
        @SerializedName("update_time")
        private Long updateTime;

        /**
         * 订单状态
         */
        private String status;

        /**
         * 订单商品总数，必须为整型字符串，例如"3"
         */
        private String amount;

        /**
         * 订单总价，必须为 Number 字符串，数字最多精确到小数点后两位(精确到分)，例如"150.24"
         */
        @JSONField(name = "total_price")
        @JsonAlias("total_price")
        @JsonProperty("total_price")
        @SerializedName("total_price")
        private String totalPrice;

        /**
         * 订单详情页 url（目前只支持小程序侧地址，例如 pages/xxxx）
         */
        @JSONField(name = "detail_url")
        @JsonAlias("detail_url")
        @JsonProperty("detail_url")
        @SerializedName("detail_url")
        private String detailUrl;

        /**
         * 订单详情页备用 url
         */
        @JSONField(name = "detail_url_backend")
        @JsonAlias("detail_url_backend")
        @JsonProperty("detail_url_backend")
        @SerializedName("detail_url_backend")
        private String detailUrlBackend;

        /**
         * 子订单商品列表
         */
        @JSONField(name = "item_list")
        @JsonAlias("item_list")
        @JsonProperty("item_list")
        @SerializedName("item_list")
        private List<Item> itemList;


    }

    @Data
    public static class Item{

        /**
         * 子订单 id
         */
        @JSONField(name = "item_code")
        @JsonAlias("item_code")
        @JsonProperty("item_code")
        @SerializedName("item_code")
        private String itemCode;

        /**
         * 子订单商品图片 url
         */
        private String img;

        /**
         * 子订单商品介绍标题
         */
        private String title;

        /**
         * 子订单商品介绍副标题
         */
        @JSONField(name = "sub_title")
        @JsonAlias("sub_title")
        @JsonProperty("sub_title")
        @SerializedName("sub_title")
        private String subTitle;

        /**
         * 单类商品的数目，必须为整型字符串，例如"2"
         */
        private String amount;

        /**
         * 单类商品的总价,必须为 Number 字符串，数字最多精确到小数点后两位(精确到分)，例如"100.24"
         */
        private String price;
    }

}
