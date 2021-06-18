package com.github.yydzxz.common.error;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceError implements Serializable {

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
    public Boolean checkSuccess(){
        return Objects.equals(this.errno, 0);
    }

    @Override
    public String toString() {
        return "错误代码：" + this.errno + ", 错误信息：" + this.message;
    }

}
