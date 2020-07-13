package com.github.yydzxz.common.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceAccessToken implements Serializable {
    private String accessToken;

    private int expiresIn = -1;
}
