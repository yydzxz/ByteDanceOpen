package com.github.yydzxz.open.api.v1.response.code;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class CodeVersionsResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -8055885355865015602L;

    private DataObj data;

    @Data
    private static class DataObj implements Serializable {

        private static final long serialVersionUID = 4806706359708124283L;

        private Audit audit;

        private Current current;

        private Latest latest;
    }

    @Data
    public static class Audit implements Serializable{

        private static final long serialVersionUID = -5005834127995462897L;

        private List<String> approvedApps;

        private Map<String,String> attachInfo;

        private List<String> categories;

        private Long ctime;

        @SerializedName("developer_avatar")
        @JSONField(name = "developer_avatar")
        @JsonAlias("developer_avatar")
        @JsonProperty("developer_avatar")
        private String developerAvatar;

        @SerializedName("developer_id")
        @JSONField(name = "developer_id")
        @JsonAlias("developer_id")
        @JsonProperty("developer_id")
        private String developerId;

        @SerializedName("developer_name")
        @JSONField(name = "developer_name")
        @JsonAlias("developer_name")
        @JsonProperty("developer_name")
        private String developerName;

        @SerializedName("has_publish")
        @JSONField(name = "has_publish")
        @JsonAlias("has_publish")
        @JsonProperty("has_publish")
        private Long hasPublish;

        @SerializedName("is_illegal_version")
        @JSONField(name = "is_illegal_version")
        @JsonAlias("is_illegal_version")
        @JsonProperty("is_illegal_version")
        private Boolean isIllegalVersion;

        private String reason;

        private Map<String, Object> reasonDetail;

        private Long status;

        private String summary;

        private String version;
    }

    @Data
    public static class Current implements Serializable{

        private static final long serialVersionUID = -5408558277908780764L;

        private List<String> approvedApps;

        private Map<String,String> attachInfo;

        private List<String> categories;

        private Long ctime;

        @SerializedName("developer_avatar")
        @JSONField(name = "developer_avatar")
        @JsonAlias("developer_avatar")
        @JsonProperty("developer_avatar")
        private String developer_avatar;

        @SerializedName("developer_id")
        @JSONField(name = "developer_id")
        @JsonAlias("developer_id")
        @JsonProperty("developer_id")
        private String developer_id;

        @SerializedName("developer_name")
        @JSONField(name = "developer_name")
        @JsonAlias("developer_name")
        @JsonProperty("developer_name")
        private String developer_name;

        @SerializedName("has_down")
        @JSONField(name = "has_down")
        @JsonAlias("has_down")
        @JsonProperty("has_down")
        private Boolean hasDown;

        private List<String> notApprovedApps;

        private String reason;

        private Map<String, Object> reasonDetail;

        private Rollback rollback;

        private String summary;

        private Long uid;

        private String version;

        @Data
        public static class Rollback{
            @SerializedName("can_rollback")
            @JSONField(name = "can_rollback")
            @JsonAlias("can_rollback")
            @JsonProperty("can_rollback")
            private Boolean canRollback;

            @SerializedName("last_version")
            @JSONField(name = "last_version")
            @JsonAlias("last_version")
            @JsonProperty("last_version")
            private String lastVersion;
        }
    }

    @Data
    public static class Latest {
        private List<String> categories;

        private Long ctime;

        @SerializedName("developer_avatar")
        @JSONField(name = "developer_avatar")
        @JsonAlias("developer_avatar")
        @JsonProperty("developer_avatar")
        private String developer_avatar;

        @SerializedName("developer_id")
        @JSONField(name = "developer_id")
        @JsonAlias("developer_id")
        @JsonProperty("developer_id")
        private String developer_id;

        @SerializedName("developer_name")
        @JSONField(name = "developer_name")
        @JsonAlias("developer_name")
        @JsonProperty("developer_name")
        private String developer_name;

        @SerializedName("has_audit")
        @JSONField(name = "has_audit")
        @JsonAlias("has_audit")
        @JsonProperty("has_audit")
        private Integer hasAudit;

        private String screenshot;

        private String summary;

        private String version;
    }

}
