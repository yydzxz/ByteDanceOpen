package com.github.yydzxz.open.api.v1.response.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class TemplateGetDraftListResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = 5546828774858997621L;

    @SerializedName("draft_list")
    @JsonProperty("draft_list")
    @JSONField(name = "draft_list")
    @JsonAlias("draft_list")
    private List<Draft> draftList;

    @Data
    public static class Draft implements Serializable {

        private static final long serialVersionUID = -4907030715430315385L;

        @SerializedName("draft_id")
        @JsonProperty("draft_id")
        @JSONField(name = "draft_id")
        @JsonAlias("draft_id")
        private Long draft_id;

        @SerializedName("user_version")
        @JsonProperty("user_version")
        @JSONField(name = "user_version")
        @JsonAlias("user_version")
        private String userVersion;

        @SerializedName("user_desc")
        @JsonProperty("user_desc")
        @JSONField(name = "user_desc")
        @JsonAlias("user_desc")
        private String userDesc;

        @SerializedName("create_time")
        @JsonProperty("create_time")
        @JSONField(name = "create_time")
        @JsonAlias("create_time")
        private Long createTime;
    }

}
