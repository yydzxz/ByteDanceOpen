package com.github.yydzxz.open.api.impl;

import cn.hutool.json.JSONUtil;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramCodeService;
import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;
import com.github.yydzxz.open.api.request.code.CodeUploadRequest;
import com.github.yydzxz.open.api.response.code.CodeAuditResponse;
import com.github.yydzxz.open.api.response.code.CodeReleaseResponse;
import com.github.yydzxz.open.api.response.code.CodeRollbackResponse;
import com.github.yydzxz.open.api.response.code.CodeUploadResponse;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
public class ByteDanceOpenMiniProgramCodeServiceImpl implements IByteDanceOpenMiniProgramCodeService {

    private IByteDanceOpenMiniProgramService byteDanceOpenMiniProgramService;

    public ByteDanceOpenMiniProgramCodeServiceImpl(IByteDanceOpenMiniProgramService byteDanceOpenMiniProgramService) {
        this.byteDanceOpenMiniProgramService = byteDanceOpenMiniProgramService;
    }

    @Override
    public CodeUploadResponse upload(CodeUploadRequest request) {
        String response = byteDanceOpenMiniProgramService.post(UPLOAD_URL, request);
        return JSONUtil.toBean(response, CodeUploadResponse.class);
    }

    @Override
    public CodeAuditResponse audit() {
        String response = byteDanceOpenMiniProgramService.post(AUDIT_URL, null);
        return JSONUtil.toBean(response, CodeAuditResponse.class);
    }

    @Override
    public CodeReleaseResponse release() {
        String response = byteDanceOpenMiniProgramService.post(RELEASE_URL, null);
        return JSONUtil.toBean(response, CodeReleaseResponse.class);
    }

    @Override
    public CodeRollbackResponse rollback() {
        String response = byteDanceOpenMiniProgramService.post(ROLLBACK_URL, null);
        return JSONUtil.toBean(response, CodeRollbackResponse.class);
    }

    @Override
    public String versions() {
        return byteDanceOpenMiniProgramService.get(VERSIONS_URL);
    }
}
