package com.github.yydzxz.open.api.v1.response.appinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/11/23
 **/
@Data
public class AppCreditScoreResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -1473053594294676243L;

    @Data
    public static class DataObj{

        /**
         * 信用分分值
         */
        @JSONField(name = "creditScore")
        @JsonAlias("creditScore")
        @JsonProperty("creditScore")
        @SerializedName("creditScore")
        private Integer creditScore;
    }
}
