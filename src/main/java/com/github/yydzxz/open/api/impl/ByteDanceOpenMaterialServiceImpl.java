package com.github.yydzxz.open.api.impl;

import com.github.yydzxz.open.api.IByteDanceOpenMaterialService;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.request.material.UploadPicMaterialRequest;
import com.github.yydzxz.open.api.response.material.UploadPicMaterialResponse;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

/**
 * @author yangyidian
 * @date 2020/07/21
 **/
@Slf4j
public class ByteDanceOpenMaterialServiceImpl implements IByteDanceOpenMaterialService {

    private IByteDanceOpenService byteDanceOpenService;

    public ByteDanceOpenMaterialServiceImpl(IByteDanceOpenService byteDanceOpenService) {
        this.byteDanceOpenService = byteDanceOpenService;
    }

    @Override
    public UploadPicMaterialResponse uploadPicMaterial(UploadPicMaterialRequest request) {
        Multimap<String, String> headers = LinkedListMultimap.create();
        headers.put("Content-Type", "multipart/form-data");
        return byteDanceOpenService
            .getByteDanceOpenComponentService()
            .postWithHeaders(UPLOAD_PIC_MATERIAL_URL, headers, request, UploadPicMaterialResponse.class);
    }
}
