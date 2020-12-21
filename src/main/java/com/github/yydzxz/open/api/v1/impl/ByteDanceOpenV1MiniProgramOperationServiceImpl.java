package com.github.yydzxz.open.api.v1.impl;

import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramOperationService;
import com.github.yydzxz.open.api.v1.IByteDanceOpenV1MiniProgramService;
import com.github.yydzxz.open.api.v1.request.operation.OperationLiveApplicationRequest;
import com.github.yydzxz.open.api.v1.request.operation.OperationPhoneNumberApplicationRequest;
import com.github.yydzxz.open.api.v1.request.operation.OperationVideoApplicationRequest;
import com.github.yydzxz.open.api.v1.response.operation.OperationLiveApplicationResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationLiveApplicationStatusResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationPhoneNumberApplicationResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationPhoneNumberApplicationStatusResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationVideoApplicationResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationVideoApplicationStatusResponse;

/**
 * @author yangyidian
 * @date 2020/12/11
 **/
public class ByteDanceOpenV1MiniProgramOperationServiceImpl implements IByteDanceOpenV1MiniProgramOperationService {

    private IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService;

    public ByteDanceOpenV1MiniProgramOperationServiceImpl(IByteDanceOpenV1MiniProgramService byteDanceOpenV1MiniProgramService) {
        this.byteDanceOpenV1MiniProgramService = byteDanceOpenV1MiniProgramService;
    }

    @Override
    public OperationVideoApplicationStatusResponse videoApplicationStatus() {
        return byteDanceOpenV1MiniProgramService.get(VIDEO_APPLICATION_STATUS_URL, OperationVideoApplicationStatusResponse.class);
    }

    @Override
    public OperationVideoApplicationResponse videoApplication(OperationVideoApplicationRequest request) {
        return byteDanceOpenV1MiniProgramService.post(VIDEO_APPLICATION_URL, request, OperationVideoApplicationResponse.class);
    }

    @Override
    public OperationLiveApplicationStatusResponse liveApplicationStatus() {
        return byteDanceOpenV1MiniProgramService.get(LIVE_APPLICATION_STATUS_URL, OperationLiveApplicationStatusResponse.class);
    }

    @Override
    public OperationLiveApplicationResponse liveApplication(OperationLiveApplicationRequest request) {
        return byteDanceOpenV1MiniProgramService.post(LIVE_APPLICATION_URL, request, OperationLiveApplicationResponse.class);
    }

    @Override
    public OperationPhoneNumberApplicationStatusResponse phoneNumberApplicationStatus() {
        return byteDanceOpenV1MiniProgramService.get(PHONE_NUMBER_APPLICATION_STATUS_URL, OperationPhoneNumberApplicationStatusResponse.class);
    }

    @Override
    public OperationPhoneNumberApplicationResponse phoneNumberApplication(OperationPhoneNumberApplicationRequest request) {
        return byteDanceOpenV1MiniProgramService.post(PHONE_NUMBER_APPLICATION_URL, request, OperationPhoneNumberApplicationResponse.class);
    }
}
