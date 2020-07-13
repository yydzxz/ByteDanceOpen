package com.github.yydzxz.open.api.request.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 将草稿设置为模版
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class TemplateAddTplRequest {

    /**
     * 草稿 ID
     */
    @JSONField(name = "draft_id")
    @JsonAlias("draft_id")
    @JsonProperty("draft_id")
    private Integer draftId;
}
