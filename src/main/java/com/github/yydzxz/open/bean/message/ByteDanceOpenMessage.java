package com.github.yydzxz.open.bean.message;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.open.util.MsgDecrypt;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
@Slf4j
@Data
public class ByteDanceOpenMessage implements Serializable {
    private static final long serialVersionUID = -4239087053795085852L;

    /** 消息类型 **/
    /**
     * Ticket消息
     */
    public static final String MSG_TYPE_TICKET = "Ticket";

    /** 事件类型 **/
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

    //    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(name = "CreateTime")
    @JsonAlias("CreateTime")
    @JsonProperty("CreateTime")
    private Date createTime;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(name = "EventTime")
    @JsonAlias("EventTime")
    @JsonProperty("EventTime")
    private Date eventTime;

    @JSONField(name = "Event")
    @JsonAlias("Event")
    @JsonProperty("Event")
    private String event;

    @JSONField(name = "FromUserName")
    @JsonAlias("FromUserName")
    @JsonProperty("FromUserName")
    private String fromUserName;

    @JSONField(name = "MsgType")
    @JsonAlias("MsgType")
    @JsonProperty("MsgType")
    private String msgType;

    @JSONField(name = "Ticket")
    @JsonAlias("Ticket")
    @JsonProperty("Ticket")
    private String ticket;

    private String fromTpAppId;

    @JSONField(name = "AppId")
    @JsonAlias("AppId")
    @JsonProperty("AppId")
    private String appId;

    @JSONField(name = "AuthorizationCode")
    @JsonAlias("AuthorizationCode")
    @JsonProperty("AuthorizationCode")
    private String authorizationCode;

    @JSONField(name = "AuthorizationCodeExpiresIn")
    @JsonAlias("AuthorizationCodeExpiresIn")
    @JsonProperty("AuthorizationCodeExpiresIn")
    private String authorizationCodeExpiresIn;

    public static ByteDanceOpenMessage fromEncrypted(String encrypted, String key) throws Exception {
        MsgDecrypt msgDecrypt = new MsgDecrypt(key);
        return msgDecrypt.decrypt(encrypted);
    }

}
