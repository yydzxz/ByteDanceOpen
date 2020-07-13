package com.github.yydzxz.open.api.request.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 删除模版
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class TemplateDelTplRequest {

    /**
     * 模版 ID
     */
    @JSONField(name = "template_id")
    @JsonAlias("template_id")
    @JsonProperty("template_id")
    private Integer templateId;
}
