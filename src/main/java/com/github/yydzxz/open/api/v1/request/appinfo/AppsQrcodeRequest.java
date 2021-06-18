package com.github.yydzxz.open.api.v1.request.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/03/12
 **/
@Data
public class AppsQrcodeRequest implements IByteDanceRequest {

    @JSONField(name = "access_token")
    @JsonAlias("access_token")
    @JsonProperty("access_token")
    @SerializedName("access_token")
    private String accessToken;

    private String appname;

    private String path;

    private Integer width;

    @JSONField(name = "line_color")
    @JsonAlias("line_color")
    @JsonProperty("line_color")
    @SerializedName("line_color")
    private RGB lineColor;

    private RGB background;

    @JSONField(name = "set_icon")
    @JsonAlias("set_icon")
    @JsonProperty("set_icon")
    @SerializedName("set_icon")
    private Boolean setIcon;

    @Data
    public static class RGB{
        private String r;

        private String g;

        private String b;
    }

}
