package com.github.yydzxz.open.api.response.code;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.service.IByteDanceResponse;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Data
public class CodeAuditResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -5789908116611917739L;
}
