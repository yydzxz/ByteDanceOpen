package com.yyd.open.bean.message;

import static com.yyd.open.util.ByteDanceBase64.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
    private Date createTime;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(name = "EventTime")
    @JsonAlias("EventTime")
    private Date eventTime;

    @JSONField(name = "Event")
    @JsonAlias("Event")
    private String event;

    @JSONField(name = "FromUserName")
    @JsonAlias("FromUserName")
    private String fromUserName;

    @JSONField(name = "MsgType")
    @JsonAlias("MsgType")
    private String msgType;

    @JSONField(name = "Ticket")
    @JsonAlias("Ticket")
    private String ticket;

    private String fromTpAppId;

    @JSONField(name = "AppId")
    @JsonAlias("AppId")
    private String appId;

    @JSONField(name = "AuthorizationCode")
    @JsonAlias("AuthorizationCode")
    private String authorizationCode;

    @JSONField(name = "AuthorizationCodeExpiresIn")
    @JsonAlias("AuthorizationCodeExpiresIn")
    private String authorizationCodeExpiresIn;

    public static ByteDanceOpenMessage fromEncrypted(String encrypted, String key) throws Exception {
        byte[] ASEKey = Base64.getDecoder().decode(key + "=");

        String decrypted = AESCBCDecrypt(encrypted, new String(ASEKey, StandardCharsets.ISO_8859_1));
        String result = decode(decrypted);
        if (result.length() < 16) {
            throw new RuntimeException("AES解密串非法，小于16位");
        }
        // 去除16位随机字符串
        String content = result.substring(16);
        // 获取消息体长度
        int[] lengthList = unpackNStar(content.substring(16, 20).getBytes(StandardCharsets.ISO_8859_1));
        int postBodyLen = lengthList[0];
        // 获取消息体
        String postBodyMsg = content.substring(20, 20 + postBodyLen);
        // 获取消息体的第三方平台 appid
        String fromTpAppId = content.substring(20 + postBodyLen);
        ByteDanceOpenMessage message = JSON.parseObject(postBodyMsg, ByteDanceOpenMessage.class);
        message.setFromTpAppId(fromTpAppId);
        return message;
    }
}
