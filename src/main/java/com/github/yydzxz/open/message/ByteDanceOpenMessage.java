package com.github.yydzxz.open.message;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.util.json.JsonSerializer;
import com.github.yydzxz.open.message.event.ApplyLiveCapabilityResults;
import com.github.yydzxz.open.message.event.ApplyPhoneNumberCapabilityResults;
import com.github.yydzxz.open.message.event.ApplyVideoCapabilityResults;
import com.github.yydzxz.open.message.event.AuditResult;
import com.github.yydzxz.open.message.event.ModifyAppIconResult;
import com.github.yydzxz.open.message.event.ModifyAppIntroResult;
import com.github.yydzxz.open.message.event.ModifyAppNameResult;
import com.github.yydzxz.open.util.MsgDecrypt;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
@Slf4j
@Data
public class ByteDanceOpenMessage implements Serializable {
    private static final long serialVersionUID = -4239087053795085852L;

    private JsonSerializer jsonSerializer;

    public static int FAILED = 0;

    public static int SUCCESS = 1;

    /************************** 消息类型 **************************/
    /**
     * Ticket消息
     */
    public static final String MSG_TYPE_TICKET = "Ticket";

    /************************** 事件类型 **************************/
    /**
     * 取消授权事件
     */
    public static final String EVENT_UNAUTHORIZED = "UNAUTHORIZED";

    /**
     * 授权事件
     */
    public static final String EVENT_AUTHORIZED = "AUTHORIZED";

    /**
     * 推送事件
     */
    public static final String EVENT_PUSH = "PUSH";

    /**
     * 代码审核结果事件
     */
    public static final String EVENT_PACKAGE_AUDIT = "PACKAGE_AUDIT";

    /**
     * 小程序修改名称的审核结果事件
     */
    public static final String EVENT_MODIFY_APP_NAME = "MODIFY_APP_NAME";

    /**
     * 如果授权小程序长时间（30天）未提交版本且未上线，名称会被重置。这时会自动触发消息发送，然后第三方应用会收到由第三方服务器推送的相应授权小程序的名称重置的通知
     * 小程序的名称重置事件
     */
    public static final String EVENT_APP_NAME_RESET_NOTIFICATION = "APP_NAME_RESET_NOTIFICATION";

    /**
     * 小程序修改简介的审核结果事件
     */
    public static final String EVENT_MODIFY_APP_INTRO = "MODIFY_APP_INTRO";

    /**
     * 小程序修改图标的审核结果事件
     */
    public static final String EVENT_MODIFY_APP_ICON = "MODIFY_APP_ICON";

    /**
     * 「短视频挂载」能力申请审核结果事件
     */
    public static final String APPLY_VIDEO_CAPABILITY = "APPLY_VIDEO_CAPABILITY";

    /**
     * 「抖音直播组件」申请的审核事件
     */
    public static final String APPLY_LIVE_CAPABILITY = "APPLY_LIVE_CAPABILITY";

    /**
     * 「获取用户手机号」能力申请审核结果通知
     */
    public static final String APPLY_PHONE_NUMBER_CAPABILITY = "APPLY_PHONE_NUMBER_CAPABILITY";

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(name = "CreateTime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonAlias("CreateTime")
    @JsonProperty("CreateTime")
    @SerializedName("CreateTime")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(name = "EventTime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonAlias("EventTime")
    @JsonProperty("EventTime")
    @SerializedName("EventTime")
    private Date eventTime;

    @JSONField(name = "Event")
    @JsonAlias("Event")
    @JsonProperty("Event")
    @SerializedName("Event")
    private String event;

    @JSONField(name = "FromUserName")
    @JsonAlias("FromUserName")
    @JsonProperty("FromUserName")
    @SerializedName("FromUserName")
    private String fromUserName;

    @JSONField(name = "MsgType")
    @JsonAlias("MsgType")
    @JsonProperty("MsgType")
    @SerializedName("MsgType")
    private String msgType;

    @JSONField(name = "Ticket")
    @JsonAlias("Ticket")
    @JsonProperty("Ticket")
    @SerializedName("Ticket")
    private String ticket;

    private String fromTpAppId;

    @JSONField(name = "TpAppId")
    @JsonAlias("TpAppId")
    @JsonProperty("TpAppId")
    @SerializedName("TpAppId")
    private String tpAppId;

    @JSONField(name = "AppId")
    @JsonAlias("AppId")
    @JsonProperty("AppId")
    @SerializedName("AppId")
    private String appId;

    @JSONField(name = "AuthorizationCode")
    @JsonAlias("AuthorizationCode")
    @JsonProperty("AuthorizationCode")
    @SerializedName("AuthorizationCode")
    private String authorizationCode;

    @JSONField(name = "AuthorizationCodeExpiresIn")
    @JsonAlias("AuthorizationCodeExpiresIn")
    @JsonProperty("AuthorizationCodeExpiresIn")
    @SerializedName("AuthorizationCodeExpiresIn")
    private String authorizationCodeExpiresIn;

    /**
     * 小程序的审核结果
     */
    @JSONField(name = "AuditResults")
    @JsonAlias("AuditResults")
    @JsonProperty("AuditResults")
    @SerializedName("AuditResults")
    private List<AuditResult> auditResults;

    /**
     * 小程序的修改名称的审核结果
     */
    @JSONField(name = "ModifyAppNameResults")
    @JsonAlias("ModifyAppNameResults")
    @JsonProperty("ModifyAppNameResults")
    @SerializedName("ModifyAppNameResults")
    private ModifyAppNameResult modifyAppNameResults;

    /**
     * 小程序的修改简介的审核结果
     */
    @JSONField(name = "ModifyAppIntroResults")
    @JsonAlias("ModifyAppIntroResults")
    @JsonProperty("ModifyAppIntroResults")
    @SerializedName("ModifyAppIntroResults")
    private ModifyAppIntroResult modifyAppIntroResults;

    /**
     * 小程序的修改图标的审核结果
     */
    @JSONField(name = "ModifyAppIconResults")
    @JsonAlias("ModifyAppIconResults")
    @JsonProperty("ModifyAppIconResults")
    @SerializedName("ModifyAppIconResults")
    private ModifyAppIconResult modifyAppIconResults;

    /**
     * 「短视频挂载」能力申请的审核结果
     */
    @JSONField(name = "ApplyVideoCapabilityResults")
    @JsonAlias("ApplyVideoCapabilityResults")
    @JsonProperty("ApplyVideoCapabilityResults")
    @SerializedName("ApplyVideoCapabilityResults")
    private ApplyVideoCapabilityResults applyVideoCapabilityResults;

    /**
     * 「抖音直播组件」申请的审核结果
     */
    @JSONField(name = "ApplyLiveCapabilityResults")
    @JsonAlias("ApplyLiveCapabilityResults")
    @JsonProperty("ApplyLiveCapabilityResults")
    @SerializedName("ApplyLiveCapabilityResults")
    private ApplyLiveCapabilityResults applyLiveCapabilityResults;

    /**
     * 「获取用户手机号」能力申请审核结果
     */
    @JSONField(name = "ApplyPhoneNumberCapabilityResults")
    @JsonAlias("ApplyPhoneNumberCapabilityResults")
    @JsonProperty("ApplyPhoneNumberCapabilityResults")
    @SerializedName("ApplyPhoneNumberCapabilityResults")
    private ApplyPhoneNumberCapabilityResults applyPhoneNumberCapabilityResults;

    public static ByteDanceOpenMessage fromEncrypted(String encrypted, String key) throws Exception {
        MsgDecrypt msgDecrypt = new MsgDecrypt(key);
        return msgDecrypt.decrypt(encrypted);
    }
}
