package com.github.yydzxz.open.api.v1.request.operation;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class OperationLiveApplicationRequest implements IByteDanceRequest {
    /**
     * 活动介绍及预估收益 字数限制：300
     */
    @JSONField(name = "intro")
    @JsonAlias("intro")
    @JsonProperty("intro")
    @SerializedName("intro")
    private String intro;

    /**
     * 预计首次开播时间
     */
    @JSONField(name = "firstLiveTime")
    @JsonAlias("firstLiveTime")
    @JsonProperty("firstLiveTime")
    @SerializedName("firstLiveTime")
    private Integer firstLiveTime;

    /**
     * 预估每周开播场次
     */
    @JSONField(name = "liveFrequency")
    @JsonAlias("liveFrequency")
    @JsonProperty("liveFrequency")
    @SerializedName("liveFrequency")
    private Integer liveFrequency;

    /**
     * 是否有广告预算
     */
    @JSONField(name = "hasAdBudget")
    @JsonAlias("hasAdBudget")
    @JsonProperty("hasAdBudget")
    @SerializedName("hasAdBudget")
    private Integer hasAdBudget;

    /**
     * 投放预算
     */
    @JSONField(name = "adBudgetAmount")
    @JsonAlias("adBudgetAmount")
    @JsonProperty("adBudgetAmount")
    @SerializedName("adBudgetAmount")
    private Integer adBudgetAmount;

    /**
     * 销售对接人姓名 字数限制：10
     */
    @JSONField(name = "adPersonName")
    @JsonAlias("adPersonName")
    @JsonProperty("adPersonName")
    @SerializedName("adPersonName")
    private String adPersonName;
}