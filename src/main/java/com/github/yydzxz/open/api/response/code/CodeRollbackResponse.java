package com.github.yydzxz.open.api.response.code;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.service.IByteDanceResponse;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class CodeRollbackResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -1181096858384303688L;
}
