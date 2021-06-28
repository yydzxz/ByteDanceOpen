package com.github.yydzxz.open.api.v1.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.yydzxz.open.api.IByteDanceOpenComponentService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramOrderPayService;
import com.github.yydzxz.open.api.v1.request.orderpay.*;
import com.github.yydzxz.open.api.v1.response.orderpay.*;
import com.github.yydzxz.open.util.SignUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ByteDanceOpenV1MiniProgramOrderPayServiceImpl implements IByteDanceOpenV1MiniProgramOrderPayService {
    @Autowired
    private Gson gson;


    private IByteDanceOpenComponentService byteDanceOpenComponentService;

    public ByteDanceOpenV1MiniProgramOrderPayServiceImpl(IByteDanceOpenComponentService byteDanceOpenComponentService) {
        this.byteDanceOpenComponentService = byteDanceOpenComponentService;
    }


    /**
     * 服务端预下单
     */
    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        return this.doPost(CREATE_ORDER_URL, request, CreateOrderResponse.class);
    }

    /**
     * 订单查询
     * @param request
     * @return
     */
    @Override
    public QueryOrderResponse queryOrder(QueryOrderRequest request) {
        return this.doPost(QUERY_ORDER_URL, request, QueryOrderResponse.class);
    }

    /**
     * 退款请求
     * @param request
     * @return
     */
    @Override
    public CreateRefundResponse createRefund(CreateRefundRequest request) {
        return this.doPost(CREATE_REFUND_URL, request, CreateRefundResponse.class);
    }

    /**
     * 查询退款
     * @param request
     * @return
     */
    @Override
    public QueryRefundResponse queryRefund(QueryRefundRequest request) {
        return this.doPost(QUERY_REFUND_URL, request, QueryRefundResponse.class);
    }

    /**
     * 分账请求
     * @param request
     * @return
     */
    @Override
    public SettleResponse settle(SettleRequest request) {
        return this.doPost(SETTLE_URL, request, SettleResponse.class);
    }

    /**
     * 查询分账
     * @param request
     * @return
     */
    @Override
    public QuerySettleResponse querySettle(QuerySettleRequest request){
        return this.doPost(QUERY_SETTLE_URL, request, QuerySettleResponse.class);
    }

    /**
     * 服务商进件
     * @param request
     * @return
     */
    @Override
    public AddMerchantResponse addMerchant(AddMerchantRequest request) {
        return this.doPost(ADD_MERCHANT_URL, request, AddMerchantResponse.class);
    }

    /**
     * 分账方进件
     * @param request
     * @return
     */
    @Override
    public AddSubMerchantResponse addSubMerchant(AddSubMerchantRequest request) {
        return this.doPost(ADD_SUB_MERCHANT_URL, request, AddSubMerchantResponse.class);
    }

    /**
     * 小程序开发者为分账方进件
     */
    @Override
    public GetAppMerchantResponse getAppMerchant(GetAppMerchantRequest request) {
        return this.doPost(GET_APP_MERCHANT_URL, request, GetAppMerchantResponse.class);
    }

    /**
     * 请求封装加密信息
     */
    public <T> T doPost(String url, OrderPayBaseRequest body, Class<T> response) {
        //整合sign
        body.setThirdpartyId(this.byteDanceOpenComponentService.getByteDanceOpenService().getByteDanceOpenConfigStorage().getComponentAppId());
        Map<String, Object>inMap = (Map<String,Object>) JSONObject.parse(gson.toJson(body));
        String sign = SignUtil.getSign(inMap, this.byteDanceOpenComponentService.getByteDanceOpenService().getByteDanceOpenConfigStorage().getPaySalt());
        inMap.put("sign", sign);
        return this.byteDanceOpenComponentService.post(url, inMap, response);
    }

}
