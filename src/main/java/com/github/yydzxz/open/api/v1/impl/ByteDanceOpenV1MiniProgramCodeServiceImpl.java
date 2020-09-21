package com.github.yydzxz.open.api.v1.impl;

import com.github.yydzxz.open.api.IByteDanceOpenMiniProgramService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramCodeService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramService;
import com.github.yydzxz.open.api.v1.request.code.CodeUploadRequest;
import com.github.yydzxz.open.api.v1.response.code.CodeAuditResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeReleaseResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeRollbackResponse;
import com.github.yydzxz.open.api.v1.response.code.CodeUploadResponse;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
public class ByteDanceOpenV1MiniProgramCodeServiceImpl implements IByteDanceOpenV1MiniProgramCodeService {

    private IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService;

    public ByteDanceOpenV1MiniProgramCodeServiceImpl(IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService) {
        this.byteDanceOpenV1MiniProgramService = byteDanceOpenV1MiniProgramService;
    }

    @Override
    public CodeUploadResponse upload(CodeUploadRequest request) {
        return byteDanceOpenV1MiniProgramService.post(UPLOAD_URL, request, CodeUploadResponse.class);
    }

    @Override
    public CodeAuditResponse audit() {
        return byteDanceOpenV1MiniProgramService.post(AUDIT_URL, null, CodeAuditResponse.class);
    }

    @Override
    public CodeReleaseResponse release() {
        return byteDanceOpenV1MiniProgramService.post(RELEASE_URL, null, CodeReleaseResponse.class);
    }

    @Override
    public CodeRollbackResponse rollback() {
        return byteDanceOpenV1MiniProgramService.post(ROLLBACK_URL, null, CodeRollbackResponse.class);
    }

    @Override
    public String versions() {
        return byteDanceOpenV1MiniProgramService.get(VERSIONS_URL);
    }
}
