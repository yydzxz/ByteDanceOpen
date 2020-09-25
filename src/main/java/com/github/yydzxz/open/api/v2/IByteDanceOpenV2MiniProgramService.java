package com.github.yydzxz.open.api.v2;

import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;

/**
 * @author yangyidian
 * @date 2020/09/18
 **/
public interface IByteDanceOpenV2MiniProgramService extends IByteDanceOpenMiniProgramService {

    /**
     * 代码包管理v2 service
     * @return
     */
    IByteDanceOpenV2MiniProgramCodeService getByteDanceOpenV2MiniProgramCodeService();

}
