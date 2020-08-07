package com.github.yydzxz.common.util.json;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/08/03
 **/
@Data
public class JsonTestClass {
    @JsonProperty("aaaaa")
    @JSONField(name = "aaaaa")
    @SerializedName("aaaaa")
    private String aBcDEFgH;

    @JsonProperty("bbbbb")
    @SerializedName("bbbbb")
    @JSONField(name = "bbbbb")
    private String AbcdEfG;
}
