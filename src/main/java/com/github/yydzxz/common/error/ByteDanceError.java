package com.github.yydzxz.common.error;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceError implements Serializable {
    /**
     * 错误代码.
     */
    private Integer errno;

    /**
     * 错误信息.
     */
    private String message;

    @Override
    public String toString() {
        return "错误代码：" + this.errno + ", 错误信息：" + this.message;
    }

}
