package com.github.yydzxz.common.error;

/**
 * @author yangyidian
 * @date 2021/06/19
 **/
public interface IByteDanceError {

    /**
     * 获取错误编码
     * @return
     */
    Integer errorCode();

    /**
     * 获取错误信息
     * @return
     */
    String errorMessage();

    Boolean checkSuccess();
}
