package com.github.yydzxz.open.api.impl;


import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.IByteDanceOpenTemplateService;
import com.github.yydzxz.open.api.request.template.TemplateAddTplRequest;
import com.github.yydzxz.open.api.request.template.TemplateDelTplRequest;
import com.github.yydzxz.open.api.response.template.TemplateAddTplResponse;
import com.github.yydzxz.open.api.response.template.TemplateDelTplResponse;
import com.github.yydzxz.open.api.response.template.TemplateGetDraftListResponse;
import com.github.yydzxz.open.api.response.template.TemplateGetTplListResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/07/01
 **/
@Slf4j
public class ByteDanceOpenTemplateServiceImpl implements IByteDanceOpenTemplateService {

    private IByteDanceOpenService byteDanceOpenService;

    public ByteDanceOpenTemplateServiceImpl(IByteDanceOpenService byteDanceOpenService) {
        this.byteDanceOpenService = byteDanceOpenService;
    }

    @Override
    public TemplateGetTplListResponse getTplList() {
        return byteDanceOpenService.getByteDanceOpenComponentService().get(GET_TPL_LIST_URL, TemplateGetTplListResponse.class);
    }

    @Override
    public TemplateGetDraftListResponse getDraftList() {
        return byteDanceOpenService.getByteDanceOpenComponentService().get(GET_DRAFT_LIST_URL, TemplateGetDraftListResponse.class);
    }

    @Override
    public TemplateAddTplResponse addTpl(TemplateAddTplRequest request) {
        return byteDanceOpenService.getByteDanceOpenComponentService().post(ADD_TPL_URL, request, TemplateAddTplResponse.class);
    }

    @Override
    public TemplateDelTplResponse delTpl(TemplateDelTplRequest request) {
        return byteDanceOpenService.getByteDanceOpenComponentService().post(DEL_TPL_URL, request, TemplateDelTplResponse.class);
    }
}
