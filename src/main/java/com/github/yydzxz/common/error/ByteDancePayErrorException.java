package com.github.yydzxz.common.error;

/**
 * @author yangyidian
 * @date 2021/06/18
 **/
public class ByteDancePayErrorException extends RuntimeException {

    private ByteDancePayError error;

    public ByteDancePayErrorException(ByteDancePayError error) {
        super(error.toString());
        this.error = error;
    }

    public ByteDancePayErrorException(Throwable cause, ByteDancePayError error) {
        super(error.toString(), cause);
        this.error = error;
    }

    public ByteDancePayError getError() {
        return this.error;
    }

}
