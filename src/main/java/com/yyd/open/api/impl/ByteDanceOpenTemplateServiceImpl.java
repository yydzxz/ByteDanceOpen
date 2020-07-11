package com.yyd.open.api.impl;


import cn.hutool.json.JSONUtil;
import com.yyd.open.api.IByteDanceOpenService;
import com.yyd.open.api.IByteDanceOpenTemplateService;
import com.yyd.open.api.request.template.TemplateAddTplRequest;
import com.yyd.open.api.request.template.TemplateDelTplRequest;
import com.yyd.open.api.response.template.TemplateAddTplResponse;
import com.yyd.open.api.response.template.TemplateDelTplResponse;
import com.yyd.open.api.response.template.TemplateGetDraftListResponse;
import com.yyd.open.api.response.template.TemplateGetTplListResponse;
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
        String response = byteDanceOpenService.getByteDanceOpenComponentService().get(GET_TPL_LIST_URL);
        return JSONUtil.toBean(response, TemplateGetTplListResponse.class);
    }

    @Override
    public TemplateGetDraftListResponse getDraftList() {
        String response = byteDanceOpenService.getByteDanceOpenComponentService().get(GET_DRAFT_LIST_URL);
        return JSONUtil.toBean(response, TemplateGetDraftListResponse.class);
    }

    @Override
    public TemplateAddTplResponse addTpl(TemplateAddTplRequest request) {
        String response = byteDanceOpenService.getByteDanceOpenComponentService().post(ADD_TPL_URL, request);
        return JSONUtil.toBean(response, TemplateAddTplResponse.class);
    }

    @Override
    public TemplateDelTplResponse delTpl(TemplateDelTplRequest request) {
        String response = byteDanceOpenService.getByteDanceOpenComponentService().post(DEL_TPL_URL, request);
        return JSONUtil.toBean(response, TemplateDelTplResponse.class);
    }
}
