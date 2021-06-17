package com.github.yydzxz.open.api.v1.request.material;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.File;

@Data
public class UploadMaterialRequest {
    @JSONField(name = "material_type")
    @JsonAlias("material_type")
    @JsonProperty("material_type")
    @SerializedName("material_type")
    private Integer materialType;

    @JSONField(name = "material_file")
    @JsonAlias("material_file")
    @JsonProperty("material_file")
    @SerializedName("material_file")
    private File materialFile;
}
