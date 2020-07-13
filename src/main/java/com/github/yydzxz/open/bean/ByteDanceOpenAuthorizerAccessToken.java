package com.github.yydzxz.open.bean;

import cn.hutool.json.JSONUtil;
import lombok.Data;

;

/**
 * @author yangyidian
 * @date 2020/06/23
 **/
@Data
public class ByteDanceOpenAuthorizerAccessToken {
    private String authorizerAccessToken;

    private int expiresIn = -1;

    public static ByteDanceOpenAuthorizerAccessToken fromJson(String json) {
        return JSONUtil.toBean(json, ByteDanceOpenAuthorizerAccessToken.class);
    }
}
