package com.github.yydzxz.open.api.response.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.yydzxz.common.error.ByteDanceError;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class TemplateGetTplListResponse extends ByteDanceError {

    private static final long serialVersionUID = -8395134596629292121L;

    @JSONField(name = "template_list")
    @JsonAlias("template_list")
    private List<Template> TemplateList;

    @Data
    public static class Template implements Serializable {

        private static final long serialVersionUID = 7892770493369875620L;

        @JSONField(name = "template_id")
        @JsonAlias("template_id")
        private Long templateId;

        @JSONField(name = "user_version")
        @JsonAlias("user_version")
        private String userVersion;

        @JSONField(name = "user_desc")
        @JsonAlias("user_desc")
        private String userDesc;

        @JSONField(name = "create_time")
        @JsonAlias("create_time")
        private Long createTime;
    }
}
