package com.github.yydzxz.open.api.v1.response.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Code2SessionResponse extends ByteDanceError implements IByteDanceResponse {

    private Code2SessionResponseData data;

    @Data
    public static class Code2SessionResponseData implements Serializable {

        @JSONField(name = "session_key")
        @JsonAlias("session_key")
        @JsonProperty("session_key")
        @SerializedName("session_key")
        private String sessionKey;

        @JSONField(name = "openid")
        @JsonAlias("openid")
        @JsonProperty("openid")
        @SerializedName("openid")
        private String openid;

        @JSONField(name = "anonymous_openid")
        @JsonAlias("anonymous_openid")
        @JsonProperty("anonymous_openid")
        @SerializedName("anonymous_openid")
        private String anonymousOpenid;
    }
}
