package com.github.yydzxz.open.api.v1.response.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.service.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class TemplateGetTplListResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -8395134596629292121L;

    @SerializedName("template_list")
    @JSONField(name = "template_list")
    @JsonAlias("template_list")
    private List<Template> TemplateList;

    @Data
    public static class Template implements Serializable {

        private static final long serialVersionUID = 7892770493369875620L;

        @SerializedName("template_id")
        @JSONField(name = "template_id")
        @JsonAlias("template_id")
        @JsonProperty("template_id")
        private Long templateId;

        @SerializedName("user_version")
        @JSONField(name = "user_version")
        @JsonAlias("user_version")
        @JsonProperty("user_version")
        private String userVersion;

        @SerializedName("user_desc")
        @JSONField(name = "user_desc")
        @JsonAlias("user_desc")
        @JsonProperty("user_desc")
        private String userDesc;

        @SerializedName("create_time")
        @JSONField(name = "create_time")
        @JsonAlias("create_time")
        @JsonProperty("create_time")
        private Long createTime;
    }
}
