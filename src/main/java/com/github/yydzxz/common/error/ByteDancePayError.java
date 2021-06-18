package com.github.yydzxz.common.error;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

/**
 * 支付错误
 * @author yangyidian
 * @date 2021/06/18
 **/
@Data
public class ByteDancePayError implements Serializable {

    /**
     * 错误号
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    public Boolean checkSuccess(){
        return Objects.equals(this.errcode, 0);
    }
}
