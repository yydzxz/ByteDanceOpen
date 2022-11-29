package com.github.yydzxz.common.error;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

/**
 * 字节跳动错误
 * 不知道字节怎么想的，要定义两套不同的错误码
 * ByteDanceError 跟 ByteDancePayError 是平级的，并不是父子关系
 * ByteDanceError的部分错误码可以在<code>ByteDanceErrorMsgEnum</code>中找到, 完整的请去官网查看
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceError implements IByteDanceError, Serializable {

    private static final long serialVersionUID = 8757224149770373443L;
    /**
     * 错误代码.
     */
    private Integer errno;

    /**
     * 错误信息.
     */
    private String message;

    /**
     * 封装结果判断
     * @return
     */
    @Override
    public Boolean checkSuccess(){
        return Objects.equals(this.errno, 0);
    }

    @Override
    public String toString() {
        return "错误代码：" + this.errno + ", 错误信息：" + this.message;
    }

    @Override
    public Integer errorCode() {
        return getErrno();
    }

    @Override
    public String errorMessage() {
        return getMessage();
    }
}
