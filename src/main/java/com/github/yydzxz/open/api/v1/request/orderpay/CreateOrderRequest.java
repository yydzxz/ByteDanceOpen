package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderRequest extends OrderPayBaseRequest {

    /**
     * 开发者侧的订单号, 同一小程序下不可重复
     */
    @JSONField(name = "out_order_no")
    @JsonAlias("out_order_no")
    @JsonProperty("out_order_no")
    @SerializedName("out_order_no")
    private String outOrderNo;

    /**
     * 支付价格; 接口中参数支付金额单位为[分]
     */
    @JSONField(name = "total_amount")
    @JsonAlias("total_amount")
    @JsonProperty("total_amount")
    @SerializedName("total_amount")
    private Integer totalAmount;

    /**
     * 商品描述; 长度限制 128 字节，不超过 42 个汉字
     */
    private String subject;

    /**
     * 商品详情
     */
    private String body;

    /**
     * 订单过期时间(秒); 最小 15 分钟，最大两天
     */
    @JSONField(name = "valid_time")
    @JsonAlias("valid_time")
    @JsonProperty("valid_time")
    @SerializedName("valid_time")
    private Integer validTime;

    /**
     * 开发者自定义字段，回调原样回传
     */
    @JSONField(name = "cp_extra")
    @JsonAlias("cp_extra")
    @JsonProperty("cp_extra")
    @SerializedName("cp_extra")
    private String cpExtra;

    /**
     * 商户自定义回调地址
     */
    @JSONField(name = "notify_url")
    @JsonAlias("notify_url")
    @JsonProperty("notify_url")
    @SerializedName("notify_url")
    private String notifyUrl;

    /**
     * 是否屏蔽担保支付的推送消息，1-屏蔽 0-非屏蔽，接入 POI 必传
     */
    @JSONField(name = "disable_msg")
    @JsonAlias("disable_msg")
    @JsonProperty("disable_msg")
    @SerializedName("disable_msg")
    private Integer disableMsg;

    /**
     * 担保支付消息跳转页
     */
    @JSONField(name = "msg_page")
    @JsonAlias("msg_page")
    @JsonProperty("msg_page")
    @SerializedName("msg_page")
    private String msgPage;

    /**
     * 多门店模式下，门店 uid
     */
    @JSONField(name = "store_uid")
    @JsonAlias("store_uid")
    @JsonProperty("store_uid")
    @SerializedName("store_uid")
    private String storeUid;

    /**
     * 商品列表，SPU 结构体数组，序列化后的字符串。
     */
    @JSONField(name = "spu_list")
    @JsonAlias("spu_list")
    @JsonProperty("spu_list")
    @SerializedName("spu_list")
    private List<Spu> spuList;

    @Data
    public static class Spu {
        /**
         * 商品 id
         */
        @JSONField(name = "spu_id")
        @JsonAlias("spu_id")
        @JsonProperty("spu_id")
        @SerializedName("spu_id")
        private Integer spuId;

        /**
         * 商品标识，用于区分订单内的同一商品，订单商品唯一
         */
        private Integer code;

        /**
         * 商品标题
         */
        private String title;

        /**
         * 商品价格，单位为分，多个商品价格之和需要等于预下单总价。退款时本字段标识对应商品的退款金额
         */
        private BigDecimal price;
    }

}
