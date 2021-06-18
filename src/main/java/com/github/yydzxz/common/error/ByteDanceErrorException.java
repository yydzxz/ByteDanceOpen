package com.github.yydzxz.common.error;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
public class ByteDanceErrorException extends RuntimeException{

    private IByteDanceError error;

    public ByteDanceErrorException(IByteDanceError error) {
        super(error.toString());
        this.error = error;
    }

    public ByteDanceErrorException(IByteDanceError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public IByteDanceError getError() {
        return this.error;
    }
}
