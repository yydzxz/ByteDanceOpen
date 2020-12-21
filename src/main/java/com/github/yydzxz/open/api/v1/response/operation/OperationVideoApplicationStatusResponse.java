package com.github.yydzxz.open.api.v1.response.operation;

import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import lombok.Data;

@Data
public class OperationVideoApplicationStatusResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = 5914133377456005753L;
}