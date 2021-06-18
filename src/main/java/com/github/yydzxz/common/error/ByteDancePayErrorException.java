package com.github.yydzxz.common.error;

/**
 * @author yangyidian
 * @date 2021/06/18
 **/
public class ByteDancePayErrorException extends RuntimeException {

    private IByteDanceError error;

    public ByteDancePayErrorException(IByteDanceError error) {
        super(error.toString());
        this.error = error;
    }

    public ByteDancePayErrorException(Throwable cause, IByteDanceError error) {
        super(error.toString(), cause);
        this.error = error;
    }

    public IByteDanceError getError() {
        return this.error;
    }

}
