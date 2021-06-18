package com.github.yydzxz.open.api.v1.response.pay;

import com.github.yydzxz.common.error.ByteDancePayError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2021/06/18
 **/
@Data
public class OrderPayAddResponse extends ByteDancePayError implements IByteDanceResponse {

    private static final long serialVersionUID = -1312228307103421626L;

}
