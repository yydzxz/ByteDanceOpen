package com.github.yydzxz.open.api.response.material;

import com.github.yydzxz.common.error.ByteDanceError;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/20
 **/
@Data
public class UploadPicMaterialResponse  extends ByteDanceError {
    private String data;
}
