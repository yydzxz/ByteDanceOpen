package com.github.yydzxz.open.api.impl;

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
        return byteDanceOpenMiniProgramService.post(UPLOAD_URL, request, CodeUploadResponse.class);
    }

    @Override
    public CodeAuditResponse audit() {
        return byteDanceOpenMiniProgramService.post(AUDIT_URL, null, CodeAuditResponse.class);
    }

    @Override
    public CodeReleaseResponse release() {
        return byteDanceOpenMiniProgramService.post(RELEASE_URL, null, CodeReleaseResponse.class);
    }

    @Override
    public CodeRollbackResponse rollback() {
        return byteDanceOpenMiniProgramService.post(ROLLBACK_URL, null, CodeRollbackResponse.class);
    }

    @Override
    public String versions() {
        return byteDanceOpenMiniProgramService.get(VERSIONS_URL);
    }
}
