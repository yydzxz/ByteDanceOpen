package com.github.yydzxz.open.bean;

import cn.hutool.json.JSONUtil;
import java.io.Serializable;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceOpenComponentAccessToken implements Serializable {

    private static final long serialVersionUID = 6417155856835777760L;

    private String componentAccessToken;

    private int expiresIn = -1;

    public static ByteDanceOpenComponentAccessToken fromJson(String json) {
        return JSONUtil.toBean(json, ByteDanceOpenComponentAccessToken.class);
    }
}
