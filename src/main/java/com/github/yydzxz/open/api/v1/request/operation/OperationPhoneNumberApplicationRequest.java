package com.github.yydzxz.open.api.v1.request.operation;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.http.IByteDanceRequest;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class OperationPhoneNumberApplicationRequest implements IByteDanceRequest {
    /**
     * 申请原因。 选项：1或2
     * 1：用于必要登录场景，2：用于收货联系方式
     */
    @JSONField(name = "reason")
    @JsonAlias("reason")
    @JsonProperty("reason")
    @SerializedName("reason")
    private Integer reason;

    /**
     * 使用场景。
     * 选项：1～7 1：账号下信息内容同步，2：网络购物，3：票务预订，4：业务办理，5：信息查询（如社保、公积金查询），6：预约，7：其他
     */
    @JSONField(name = "scene")
    @JsonAlias("scene")
    @JsonProperty("scene")
    @SerializedName("scene")
    private Integer scene;

    /**
     * 场景描述。字数限制：10~200
     */
    @JSONField(name = "description")
    @JsonAlias("description")
    @JsonProperty("description")
    @SerializedName("description")
    private String description;

    /**
     * 场景示例图列表。
     * 注意：
     * 需要使用 上传图片材料 接口返回的路径才可以，否则报错。
     * 目前仅支持传入 1 张图片的路径，若有多张场景示例图，请将它们拼接为 1 张图片后上传并在此处传入长度为 1 的场景示例图路径列表。
     */
    @JSONField(name = "imagePaths")
    @JsonAlias("imagePaths")
    @JsonProperty("imagePaths")
    @SerializedName("imagePaths")
    private List<String> imagePaths;
}