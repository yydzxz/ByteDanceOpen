package com.github.yydzxz.common.error;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
public class InvalidAuthorizerRefreshToken extends RuntimeException {

    public InvalidAuthorizerRefreshToken() {
    }

    public InvalidAuthorizerRefreshToken(String message) {
        super(message);
    }
}
