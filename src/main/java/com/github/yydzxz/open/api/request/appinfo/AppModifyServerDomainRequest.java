package com.github.yydzxz.open.api.request.appinfo;

import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/20
 **/
@Data
public class AppModifyServerDomainRequest {

    private String request;

    private String socket;

    private String upload;

    private String download;

    /**
     * add 添加，delete 删除，set 覆盖，get 获取
     */
    private String action;
}
