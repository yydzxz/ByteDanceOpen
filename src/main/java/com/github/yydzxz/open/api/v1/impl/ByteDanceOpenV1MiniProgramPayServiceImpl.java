package com.github.yydzxz.open.api.v1.impl;

import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramPayService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramService;
import com.github.yydzxz.open.api.v1.request.pay.OrderPayAddRequest;
import com.github.yydzxz.open.api.v1.request.pay.OrderPayDeleteRequest;
import com.github.yydzxz.open.api.v1.request.pay.OrderPayUpdateRequest;
import com.github.yydzxz.open.api.v1.response.pay.OrderPayAddResponse;
import com.github.yydzxz.open.api.v1.response.pay.OrderPayDeleteResponse;
import com.github.yydzxz.open.api.v1.response.pay.OrderPayUpdateResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2021/06/18
 **/
@Slf4j
public class ByteDanceOpenV1MiniProgramPayServiceImpl implements IByteDanceOpenV1MiniProgramPayService {

    private IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService;

    public ByteDanceOpenV1MiniProgramPayServiceImpl(IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService) {
        this.byteDanceOpenV1MiniProgramService = byteDanceOpenV1MiniProgramService;
    }

    @Override
    public OrderPayAddResponse orderAdd(OrderPayAddRequest request) {
        return byteDanceOpenV1MiniProgramService.post(ORDER_ADD_URL, request, OrderPayAddResponse.class);
    }

    @Override
    public OrderPayUpdateResponse orderUpdate(OrderPayUpdateRequest request) {
        return byteDanceOpenV1MiniProgramService.post(ORDER_UPDATE_URL, request, OrderPayUpdateResponse.class);
    }

    @Override
    public OrderPayDeleteResponse orderDelete(OrderPayDeleteRequest request) {
        return byteDanceOpenV1MiniProgramService.post(ORDER_DELETE_URL, request, OrderPayDeleteResponse.class);
    }
}
