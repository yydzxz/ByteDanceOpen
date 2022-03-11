package com.github.yydzxz.open.api.v1.response.material;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/20
 **/
@Data
public class UploadPicMaterialResponse extends ByteDanceError implements IByteDanceResponse {
    private String data;
}
