package com.github.yydzxz.open.api.v2.request.code;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class CodeAuditRequest implements IByteDanceRequest {
    @JSONField(name = "hostNames")
    @JsonAlias("hostNames")
    @JsonProperty("hostNames")
    @SerializedName("hostNames")
    private List<String> hostNames;
}
