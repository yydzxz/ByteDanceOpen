package com.github.yydzxz.open.api.v1.request.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderPayBaseRequest implements Serializable {
    /**
     * 小程序 id
     */
    @JSONField(name = "app_id")
    @JsonAlias("app_id")
    @JsonProperty("app_id")
    @SerializedName("app_id")
    private String appId;

    /**
     * 第三方平台服务商 id，非服务商模式留空
     */
    @JSONField(name = "thirdparty_id")
    @JsonAlias("thirdparty_id")
    @JsonProperty("thirdparty_id")
    @SerializedName("thirdparty_id")
    private String thirdpartyId;

    /**
     * 开发者对核心字段签名, 签名方式见文档, 防止传输过程中出现意外
     */
    private String sign;
}
