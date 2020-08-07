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
public class AppModifyAppNameRequest implements IByteDanceRequest {

    /**
     * 授权小程序准备修改的名称
     */
    @JSONField(name = "new_name")
    @JsonAlias("new_name")
    @JsonProperty("new_name")
    @SerializedName("new_name")
    private String newName;

    /**
     * 如果调用 小程序名称检测 接口，返回 21002 错误，则必须先调用 上传图片材料 接口上传证明。
     *
     * 另外，需要使用 上传图片材料 接口返回的路径才可以，否则报错。
     */
    @JSONField(name = "material_file_path")
    @JsonAlias("material_file_path")
    @JsonProperty("material_file_path")
    @SerializedName("material_file_path")
    private String materialFilePath;
}
