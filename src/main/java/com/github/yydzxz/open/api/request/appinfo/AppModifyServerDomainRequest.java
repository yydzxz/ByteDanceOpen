package com.github.yydzxz.open.api.request.appinfo;

import java.util.List;
import lombok.Data;

/**
 * 修改服务器域名
 * @author yangyidian
 * @date 2020/07/20
 **/
@Data
public class AppModifyServerDomainRequest {

    private List<String> request;

    private List<String> socket;

    private List<String> upload;

    private List<String> download;

    /**
     * add 添加，delete 删除，set 覆盖，get 获取
     */
    private String action;
}
