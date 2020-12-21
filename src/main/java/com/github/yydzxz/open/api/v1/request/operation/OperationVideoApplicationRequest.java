package com.github.yydzxz.open.api.v1.request.operation;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class OperationVideoApplicationRequest implements IByteDanceRequest {
    /**
     * 申请原因以及挂载内容介绍
     * 字数限制：200
     */
    @JSONField(name = "intro")
    @JsonAlias("intro")
    @JsonProperty("intro")
    @SerializedName("intro")
    private String intro;

    /**
     * 挂载锚点文案示例
     * 字数限制：200
     */
    @JSONField(name = "anchorText")
    @JsonAlias("anchorText")
    @JsonProperty("anchorText")
    @SerializedName("anchorText")
    private String anchortext;

    /**
     * 小程序落地页截图
     * 注意：需要使用 上传图片材料 接口返回的路径才可以，否则报错。
     */
    @JSONField(name = "imagePath")
    @JsonAlias("imagePath")
    @JsonProperty("imagePath")
    @SerializedName("imagePath")
    private String imagepath;
}