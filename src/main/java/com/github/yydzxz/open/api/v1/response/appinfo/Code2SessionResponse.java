package com.github.yydzxz.open.api.v1.response.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Code2SessionResponse extends ByteDanceError implements IByteDanceResponse {

    private Code2SessionResponseData data;

    @Data
    public static class Code2SessionResponseData{

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

        /**
         * 用户在小程序平台的唯一标识符，如果请求时有 code 参数才会返回。如果开发者拥有多个小程序，可通过 unionid 来区分用户的唯一性。
         */
        private String unionid;
    }
}
