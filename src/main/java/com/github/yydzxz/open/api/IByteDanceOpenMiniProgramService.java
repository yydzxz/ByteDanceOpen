package com.github.yydzxz.open.api;

import com.github.yydzxz.common.service.IByteDanceHttpRequestService;
import com.google.common.collect.Multimap;

/**
 * 开放平台代小程序实现服务能力(代授权小程序业务)
 * https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#zJEn6j
 * @author yangyidian
 * @date 2020/06/22
 **/
public interface IByteDanceOpenMiniProgramService {
//    IByteDanceHttpRequestService getByteDanceHttpRequestService();

    /**
     * 代码包管理service
     * @return
     */
    IByteDanceOpenMiniProgramCodeService getByteDanceOpenMiniProgramCodeService();

    /**
     * 基本信息配置service
     * @return
     */
    IByteDanceOpenMiniProgramInfoService getByteDanceOpenMiniProgramInfoService();

    IByteDanceOpenComponentService getByteDanceOpenComponentService();

    String getAccessToken();

    String getAccessToken(boolean forceRefresh);

    String get(String url);

    <T> T get(String url, Class<T> t);

    <T> T post(String url, Object request, Class<T> t);

    <T> T postWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t);
}
