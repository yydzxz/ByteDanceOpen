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
    IByteDanceOpenV1MiniProgramCodeService getByteDanceOpenV1MiniProgramCodeService();

    /**
     * 基本信息配置service
     * @return
     */
    IByteDanceOpenV1MiniProgramInfoService getByteDanceOpenV1MiniProgramInfoService();

    /**
     * 运营管理service
     * @return
     */
    IByteDanceOpenV1MiniProgramOperationService getByteDanceOpenV1MiniProgramOperationService();

    /**
     * 支付service
     * @return
     */
    IByteDanceOpenV1MiniProgramPayService getByteDanceOpenV1MiniProgramPayService();

    IByteDanceOpenV1ComponentService getByteDanceOpenV1ComponentService();

}
