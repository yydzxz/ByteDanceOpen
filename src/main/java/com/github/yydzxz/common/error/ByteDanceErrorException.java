package com.github.yydzxz.common.error;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
public class ByteDanceErrorException extends RuntimeException{

    private ByteDanceError error;

    public ByteDanceErrorException(ByteDanceError error) {
        super(error.toString());
        this.error = error;
    }

    public ByteDanceErrorException(ByteDanceError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public ByteDanceError getError() {
        return this.error;
    }
}
