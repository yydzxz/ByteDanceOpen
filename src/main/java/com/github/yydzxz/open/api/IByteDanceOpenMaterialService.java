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

    /**
     * 使用修改名称、图标、服务类目等涉及材料证明的接口前，都需要先使用这个图片上传接口拿到返回的图片地址。目前只支持bmp、jpeg、jpg、png格式。
     * @param request
     * @return
     */
    UploadPicMaterialResponse uploadPicMaterial(UploadPicMaterialRequest request);
}
