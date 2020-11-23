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
public class AppQualityRatingResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -4298198524554688226L;

    private DataObj data;

    @Data
    public static class DataObj{

        /**
         * 质量评级状态，1：未发布、未评级，2：已发布、评级中，3：已评级
         * 如果状态是未评级或评级中，则 qualityRating 为空字符串
         */
        private Integer status;
        /**
         * 质量评级分数对应的等级 S、A、B、C
         */
        @JSONField(name = "qualityRating")
        @JsonAlias("qualityRating")
        @JsonProperty("qualityRating")
        @SerializedName("qualityRating")
        private String qualityRating;
    }
}
