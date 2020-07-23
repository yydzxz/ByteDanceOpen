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
public class TemplateGetDraftListResponse extends ByteDanceError {

    private static final long serialVersionUID = 5546828774858997621L;

    @JSONField(name = "draft_list")
    @JsonAlias("draft_list")
    private List<Draft> draftList;

    @Data
    public static class Draft implements Serializable {

        private static final long serialVersionUID = -4907030715430315385L;

        @JSONField(name = "draft_id")
        @JsonAlias("draft_id")
        private Long draft_id;

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
