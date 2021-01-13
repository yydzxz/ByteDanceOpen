package com.github.yydzxz.open.message.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 提审结果
 * @author yangyidian
 * @date 2020/09/23
 **/
@Data
public class AuditResult implements Serializable {

    private static final long serialVersionUID = 7998839283597570430L;

    /**
     * 宿主端英文简称
     * com.github.yydzxz.common.enums.HostEnum
     */
    @JSONField(name = "hostName")
    @JsonAlias("hostName")
    @JsonProperty("hostName")
    @SerializedName("hostName")
    private String hostName;
    /**
     * 如果被拒，被拒原因
     */
    private List<String> reason;
    /**
     * 0或1，0代表不通过，1代表通过
     */
    private Integer status;

    /**
     * 审核的具体细节，通过时为 null
     */
    @JSONField(name = "auditDetail")
    @JsonAlias("auditDetail")
    @JsonProperty("auditDetail")
    @SerializedName("auditDetail")
    private List<AuditDetail> auditDetail;


    @Data
    public static class AuditDetail implements Serializable{

        private static final long serialVersionUID = 5212066817723501987L;

        /**
         * 被拒原因
         */
        private String reason;

        /**
         * 修改建议
         */
        private ModifyGuide modifyGuide;

        /**
         * 审核拒绝描述附件，可能有可能没有
         */
        private String detailFile;

    }

    @Data
    public static class ModifyGuide implements Serializable{

        private static final long serialVersionUID = -5149863907080937339L;

        /**
         * 修改指引名
         */
        private String name;

        /**
         * 修改指引链接
         */
        private String link;
    }
}
