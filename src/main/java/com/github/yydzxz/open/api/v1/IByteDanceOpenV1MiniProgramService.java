package com.github.yydzxz.open.api.v1;

import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;

/**
 * 开放平台代小程序实现服务能力(代授权小程序业务)
 * https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#zJEn6j
 * @author yangyidian
 * @date 2020/06/22
 **/
public interface IByteDanceOpenV1MiniProgramService extends IByteDanceOpenMiniProgramService {
    /**
     * 代码包管理v1 service
     * @return
     */
    IByteDanceOpenV1MiniProgramCodeService getByteDanceOpenMiniProgramCodeService();

    /**
     * 基本信息配置service
     * @return
     */
    IByteDanceOpenV1MiniProgramInfoService getByteDanceOpenMiniProgramInfoService();

    IByteDanceOpenV1ComponentService getByteDanceOpenV1ComponentService();

}
