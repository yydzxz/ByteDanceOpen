package com.github.yydzxz.open.api.response.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.yydzxz.common.error.ByteDanceError;
import java.util.List;
import lombok.Data;

/**
 * 获取小程序基本信息返回值
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class AppInfoResponse extends ByteDanceError {

    private AppInfoResponseData data;

    @Data
    public static class AppInfoResponseData{
        @JSONField(name = "app_id")
        @JsonAlias("app_id")
        private String appId;

        @JSONField(name = "app_type")
        @JsonAlias("app_type")
        private String appType;

        @JSONField(name = "app_state")
        @JsonAlias("app_state")
        private String appState;

        @JSONField(name = "app_name")
        @JsonAlias("app_name")
        private String appName;

        @JSONField(name = "new_name_audit_info")
        @JsonAlias("new_name_audit_info")
        private NewNameAuditInfo newNameAuditInfo;

        @JSONField(name = "app_intro")
        @JsonAlias("app_intro")
        private String appIntro;

        @JSONField(name = "new_intro_audit_info")
        @JsonAlias("new_intro_audit_info")
        private NewIntroAuditInfo newIntroAuditInfo;

        @JSONField(name = "app_icon")
        @JsonAlias("app_icon")
        private String appIcon;

        @JSONField(name = "new_icon_audit_info")
        @JsonAlias("new_icon_audit_info")
        private NewIconAuditInfo newIconAuditInfo;

        @JSONField(name = "app_categories_audit_info")
        @JsonAlias("app_categories_audit_info")
        private List<AppCategoriesAuditInfo> appCategoriesAuditInfo;

        @JSONField(name = "subject_audit_info")
        @JsonAlias("subject_audit_info")
        private SubjectAuditInfo subjectAuditInfo;
    }

    @Data
    public static class NewNameAuditInfo{
        @JSONField(name = "new_name")
        @JsonAlias("new_name")
        private String newName;

        @JSONField(name = "remaining_times")
        @JsonAlias("remaining_times")
        private String remainingTimes;

        @JSONField(name = "new_name_audit_state")
        @JsonAlias("new_name_audit_state")
        private String newNameAuditState;
        private String reason;
        private String advice;
    }

    @Data
    public static class NewIntroAuditInfo{
        @JSONField(name = "new_intro")
        @JsonAlias("new_intro")
        private String newIntro;

        @JSONField(name = "remaining_times")
        @JsonAlias("remaining_times")
        private String remainingTimes;

        @JSONField(name = "new_intro_audit_state")
        @JsonAlias("new_intro_audit_state")
        private String newIntroAuditState;
        private String reason;
        private String advice;
    }

    @Data
    public static class NewIconAuditInfo{
        @JSONField(name = "new_icon")
        @JsonAlias("new_icon")
        private String newIcon;

        @JSONField(name = "remaining_times")
        @JsonAlias("remaining_times")
        private String remainingTimes;

        @JSONField(name = "new_icon_audit_state")
        @JsonAlias("new_icon_audit_state")
        private String newIconAuditState;
        private String reason;
        private String advice;
    }


    @Data
    public static class AppCategoriesAuditInfo{
        @JSONField(name = "app_category")
        @JsonAlias("app_category")
        private String appCategory;

        @JSONField(name = "app_category_name")
        @JsonAlias("app_category_name")
        private String appCategoryName;

        @JSONField(name = "app_category_audit_state")
        @JsonAlias("app_category_audit_state")
        private String appCategoryAuditState;
        private String reason;
    }

    @Data
    public static class SubjectAuditInfo{
        @JSONField(name = "subject_number")
        @JsonAlias("subject_number")
        private String subjectNumber;

        @JSONField(name = "subject_name")
        @JsonAlias("subject_name")
        private String subjectName;

        @JSONField(name = "subject_type")
        @JsonAlias("subject_type")
        private String subjectType;

        @JSONField(name = "subject_audit_state")
        @JsonAlias("subject_audit_state")
        private String subjectAuditState;
        private String reason;

    }
}
