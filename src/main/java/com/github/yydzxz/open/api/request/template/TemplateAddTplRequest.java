package com.github.yydzxz.open.api.request.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.service.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 将草稿设置为模版
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class TemplateAddTplRequest implements IByteDanceRequest {

    /**
     * 草稿 ID
     */
    @JSONField(name = "draft_id")
    @JsonAlias("draft_id")
    @JsonProperty("draft_id")
    @SerializedName("draft_id")
    private Integer draftId;
}
