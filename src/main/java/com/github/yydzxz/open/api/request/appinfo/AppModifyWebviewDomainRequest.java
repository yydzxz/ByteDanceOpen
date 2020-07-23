package com.github.yydzxz.open.api.request.appinfo;

import java.util.List;
import lombok.Data;

/**
 * 修改 webview 域名
 * @author yangyidian
 * @date 2020/07/20
 **/
@Data
public class AppModifyWebviewDomainRequest {

    /**
     * add 添加，delete 删除，set 覆盖，get 获取
     * 若不填此项，默认将第三方平台配置好的 webview 域名全部添加到授权小程序
     */
    private String action;

    private List<String> webview;
}
