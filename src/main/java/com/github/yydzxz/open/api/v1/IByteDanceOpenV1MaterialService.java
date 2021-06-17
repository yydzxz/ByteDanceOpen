package com.github.yydzxz.open.api.v1;

import com.github.yydzxz.open.api.v1.request.material.UploadMaterialRequest;
import com.github.yydzxz.open.api.v1.request.material.UploadPicMaterialRequest;
import com.github.yydzxz.open.api.v1.response.material.UploadMaterialResponse;
import com.github.yydzxz.open.api.v1.response.material.UploadPicMaterialResponse;

/**
 * 图片材料
 * @author yangyidian
 * @date 2020/07/20
 **/
public interface IByteDanceOpenV1MaterialService {
    String UPLOAD_PIC_MATERIAL_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/upload_pic_material";

    /**
     * 上传授权小程序素材
     */
    String UPLOAD_MATERIAL_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/upload_material";

    /**
     * 2021-08-01后弃用
     * 使用修改名称、图标、服务类目等涉及材料证明的接口前，都需要先使用这个图片上传接口拿到返回的图片地址。目前只支持bmp、jpeg、jpg、png格式。
     * @param request
     * @return
     */
    UploadPicMaterialResponse uploadPicMaterial(UploadPicMaterialRequest request);

    /**
     * 新上传素材接口
     * 使用修改名称、图标、服务类目等涉及材料证明的接口前，都需要先使用这个图片上传接口拿到返回的图片地址。目前只支持bmp、jpeg、jpg、png格式。
     */
    UploadMaterialResponse uploadMaterial(UploadMaterialRequest request);
}
