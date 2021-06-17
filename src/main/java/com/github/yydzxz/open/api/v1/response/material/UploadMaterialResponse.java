package com.github.yydzxz.open.api.v1.response.material;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import lombok.Data;

@Data
public class UploadMaterialResponse extends ByteDanceError implements IByteDanceResponse {

    private UploadMaterialResponseData data;

    @Data
    public static class UploadMaterialResponseData{
        private String path;
    }
}
