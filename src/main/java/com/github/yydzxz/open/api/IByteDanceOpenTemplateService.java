package com.github.yydzxz.open.api;

import com.github.yydzxz.open.api.request.template.TemplateAddTplRequest;
import com.github.yydzxz.open.api.response.template.TemplateGetDraftListResponse;
import com.github.yydzxz.open.api.response.template.TemplateGetTplListResponse;
import com.github.yydzxz.open.api.request.template.TemplateDelTplRequest;
import com.github.yydzxz.open.api.response.template.TemplateAddTplResponse;
import com.github.yydzxz.open.api.response.template.TemplateDelTplResponse;

/**
 * 模版管理
 * @author yangyidian
 * @date 2020/07/01
 **/
public interface IByteDanceOpenTemplateService {

    /**
     * 获取第三方应用的所有模版
     */
    String GET_TPL_LIST_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/template/get_tpl_list";

    /**
     * 获取第三方应用的草稿
     */
    String GET_DRAFT_LIST_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/template/get_draft_list";

    /**
     * 将草稿设置为模版
     */
    String ADD_TPL_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/template/add_tpl";

    /**
     * 删除模版
     */
    String DEL_TPL_URL = "https://open.microapp.bytedance.com/openapi/v1/tp/template/del_tpl";

    /**
     * 获取第三方应用的所有模版
     * @return
     */
    TemplateGetTplListResponse getTplList();

    /**
     * 获取第三方应用的草稿
     * @return
     */
    TemplateGetDraftListResponse getDraftList();

    /**
     * 将草稿设置为模版
     * @param request
     * @return
     */
    TemplateAddTplResponse addTpl(TemplateAddTplRequest request);

    /**
     * 删除模版
     * @param request
     * @return
     */
    TemplateDelTplResponse delTpl(TemplateDelTplRequest request);

}
