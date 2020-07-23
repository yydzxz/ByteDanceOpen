package com.github.yydzxz.open.api;

import com.github.yydzxz.open.api.request.material.UploadPicMaterialRequest;
import com.github.yydzxz.open.api.response.material.UploadPicMaterialResponse;

/**
 * 图片材料
 * @author yangyidian
 * @date 2020/07/20
 **/
public interface IByteDanceOpenMaterialService {
    String UPLOAD_PIC_MATERIAL_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/upload_pic_material";

    UploadPicMaterialResponse uploadPicMaterial(UploadPicMaterialRequest request);
}
