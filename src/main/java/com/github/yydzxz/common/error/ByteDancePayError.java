package com.github.yydzxz.common.error;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

/**
 * 字节跳动支付错误
 *
 * ByteDancePayError的部分错误码可以在<code>ByteDancePayErrorMsgEnum</code>中找到, 完整的请去官网查看
 * @author yangyidian
 * @date 2021/06/18
 **/
@Data
public class ByteDancePayError implements IByteDanceError, Serializable {

    private static final long serialVersionUID = 3633468442265803862L;
    /**
     * 错误号
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    @Override
    public Boolean checkSuccess(){
        return Objects.equals(this.errcode, 0);
    }

    @Override
    public String toString() {
        return "错误代码：" + this.errcode + ", 错误信息：" + this.errmsg;
    }

    @Override
    public Integer errorCode() {
        return getErrcode();
    }

    @Override
    public String errorMessage() {
        return getErrmsg();
    }
}
