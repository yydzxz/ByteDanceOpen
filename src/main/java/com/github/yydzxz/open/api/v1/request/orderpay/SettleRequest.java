package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SettleRequest  extends OrderPayBaseRequest {

    /**
     * 开发者侧的订单号, 同一小程序下不可重复
     */
    @JSONField(name = "out_order_no")
    @JsonAlias("out_order_no")
    @JsonProperty("out_order_no")
    @SerializedName("out_order_no")
    private String outOrderNo;

    /**
     * 开发者侧的结算号, 不可重复
     */
    @JSONField(name = "out_settle_no")
    @JsonAlias("out_settle_no")
    @JsonProperty("out_settle_no")
    @SerializedName("out_settle_no")
    private String outSettleNo;

    /**
     * 结算描述，长度限制 80 个字符
     */
    @JSONField(name = "settle_desc")
    @JsonAlias("settle_desc")
    @JsonProperty("settle_desc")
    @SerializedName("settle_desc")
    private String settleDesc;

    /**
     * 其他分账方信息，分账分配参数 SettleParameter 数组序列化后生成的 json 格式字符串
     */
    @JSONField(name = "settle_params")
    @JsonAlias("settle_params")
    @JsonProperty("settle_params")
    @SerializedName("settle_params")
    private List<SettleParameter> settleParams;


    /**
     * 开发者自定义字段，回调原样回传
     */
    @JSONField(name = "cp_extra")
    @JsonAlias("cp_extra")
    @JsonProperty("cp_extra")
    @SerializedName("cp_extra")
    private String cpExtra;

    @Data
    public static class SettleParameter{

        /**
         * 分账方商户号
         */
        @JSONField(name = "merchant_uid")
        @JsonAlias("merchant_uid")
        @JsonProperty("merchant_uid")
        @SerializedName("merchant_uid")
        private String merchantUid;

        /**
         * 分账金额 单位[分]
         */
        private Integer amount;
    }
}
