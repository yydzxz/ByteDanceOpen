package com.github.yydzxz.open.api.v1.response.code;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/03/12
 **/
@Data
public class CensorImageResponse extends ByteDanceError implements IByteDanceResponse {

    private List<Predict> predicts;

    @Data
    public static class Predict{

        /**
         * 检测结果-置信度-模型/标签
         */
        @SerializedName("model_name")
        @JSONField(name = "model_name")
        @JsonAlias("model_name")
        @JsonProperty("model_name")
        private String modelName;

        /**
         * 检测结果-置信度-结果，当值为 true 时表示检测的图片包含违法违规内容，比如是广告
         */
        private Boolean hit;
    }
}
