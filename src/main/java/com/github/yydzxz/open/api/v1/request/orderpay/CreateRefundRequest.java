package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateRefundRequest  extends OrderPayBaseRequest {
    /**
     * 商户分配订单号，标识进行退款的订单
     */
    @JSONField(name = "out_order_no")
    @JsonAlias("out_order_no")
    @JsonProperty("out_order_no")
    @SerializedName("out_order_no")
    private String outOrderNo;


    /**
     * 商户分配退款号
     */
    @JSONField(name = "out_refund_no")
    @JsonAlias("out_refund_no")
    @JsonProperty("out_refund_no")
    @SerializedName("out_refund_no")
    private String outRefundNo;


    /**
     * 退款金额，单位[分]
     */
    @JSONField(name = "refund_amount")
    @JsonAlias("refund_amount")
    @JsonProperty("refund_amount")
    @SerializedName("refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款理由，长度上限 100
     */
    @JSONField(name = "reason")
    @JsonAlias("reason")
    @JsonProperty("reason")
    @SerializedName("reason")
    private String reason;

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
     * 是否屏蔽担保支付消息，1-屏蔽
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
     * 是否为分账后退款，1-分账后退款；0-分账前退款。分账后退款会扣减可提现金额，请保证余额充足
     */
    @JSONField(name = "all_settle")
    @JsonAlias("all_settle")
    @JsonProperty("all_settle")
    @SerializedName("all_settle")
    private String allSettle;

    /**
     * 退款商品列表，SPU 结构体数组（见预下单接口），序列化后的字符串。
     */
    @JSONField(name = "spu_list")
    @JsonAlias("spu_list")
    @JsonProperty("spu_list")
    @SerializedName("spu_list")
    private List<CreateOrderRequest.Spu> spuList;

}
