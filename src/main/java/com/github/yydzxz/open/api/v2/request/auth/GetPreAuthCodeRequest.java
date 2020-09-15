package com.github.yydzxz.open.api.v2.request.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.service.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/09/15
 **/
@Data
public class GetPreAuthCodeRequest implements IByteDanceRequest {
    @JSONField(name = "share_ratio")
    @JsonAlias("share_ratio")
    @JsonProperty("share_ratio")
    @SerializedName("share_ratio")
    private Integer shareRatio;

    @JSONField(name = "share_amount")
    @JsonAlias("share_amount")
    @JsonProperty("share_amount")
    @SerializedName("share_amount")
    private Integer shareAmount;

}
