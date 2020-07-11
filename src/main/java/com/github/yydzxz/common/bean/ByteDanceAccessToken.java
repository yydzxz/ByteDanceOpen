package com.github.yydzxz.common.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
public class ByteDanceAccessToken implements Serializable {

    private static final long serialVersionUID = 4456833691693275326L;

    private String accessToken;

    private int expiresIn = -1;
}
