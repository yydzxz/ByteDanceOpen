package com.github.yydzxz.open.api.v1;

import com.github.yydzxz.open.api.v1.request.code.CodeUploadRequest;
import com.github.yydzxz.open.api.v1.response.code.CodeAuditHostsResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeAuditResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeReleaseResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeRollbackResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeUploadResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeVersionsResponse;

/**
 * 代授权小程序业务-代码包管理 相关API
 * @author yangyidian
 * @date 2020/06/24
 **/
public interface IByteDanceOpenV1MiniProgramCodeService {

    /**
     * 提交代码
     */
    String UPLOAD_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/package/upload";

    /**
     * 获取可选审核宿主端列表
     */
    String AUDIT_HOSTS_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/package/audit_hosts";

    /**
     * 提审代码
     */
    String AUDIT_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/package/audit";

    /**
     * 发布代码
     */
    String RELEASE_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/package/release";

    /**
     * 回退代码
     */
    String ROLLBACK_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/package/rollback";

    /**
     * 获取小程序版本列表信息
     */
    String VERSIONS_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/package/versions";

    /**
     * 提交代码
     * @param request
     * @return
     */
    CodeUploadResponse upload(CodeUploadRequest request);

    CodeAuditHostsResponse auditHosts();

    /**
     * 提审代码
     * @return
     */
    CodeAuditResponse audit();

    /**
     * 发布代码
     * @return
     */
    CodeReleaseResponse release();

    /**
     * 回退代码
     * @return
     */
    CodeRollbackResponse rollback();

    /**
     * 获取小程序版本列表信息
     * @return
     */
    CodeVersionsResponse versions();

}
