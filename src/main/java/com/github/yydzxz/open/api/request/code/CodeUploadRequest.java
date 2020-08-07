package com.github.yydzxz.open.api.request.code;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.service.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 提交代码
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class CodeUploadRequest implements IByteDanceRequest {

    /**
     * 模版 ID
     */
    @JSONField(name = "template_id")
    @JsonAlias("template_id")
    @JsonProperty("template_id")
    @SerializedName("template_id")
    private Long templateId;
    /**
     * 提交描述
     */
    @JSONField(name = "user_desc")
    @JsonAlias("user_desc")
    @JsonProperty("user_desc")
    @SerializedName("user_desc")
    private String userDesc;
    /**
     * 提交版本
     */
    @JSONField(name = "user_version")
    @JsonAlias("user_version")
    @JsonProperty("user_version")
    @SerializedName("user_version")
    private String userVersion;
    /**
     * ext_json 配置信息，必须是 JSON String
     */
    @JSONField(name = "ext_json")
    @JsonAlias("ext_json")
    @JsonProperty("ext_json")
    @SerializedName("ext_json")
    private String extJson;

}
