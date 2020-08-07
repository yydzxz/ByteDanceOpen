package com.github.yydzxz.open.api.request.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.service.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/20
 **/
@Data
public class AppModifyAppIntroRequest implements IByteDanceRequest {

    /**
     * 授权小程序准备修改的简介
     */
    @JSONField(name = "new_intro")
    @JsonAlias("new_intro")
    @JsonProperty("new_intro")
    @SerializedName("new_intro")
    private String newIntro;

}
