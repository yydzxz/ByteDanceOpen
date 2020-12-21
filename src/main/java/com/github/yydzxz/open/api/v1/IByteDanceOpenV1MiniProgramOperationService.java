package com.github.yydzxz.open.api.v1;

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
 * 运营管理
 * @author yangyidian
 * @date 2020/12/11
 **/
public interface IByteDanceOpenV1MiniProgramOperationService {


    String VIDEO_APPLICATION_STATUS_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/operation/video_application_status";

    String VIDEO_APPLICATION_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/operation/video_application";

    String LIVE_APPLICATION_STATUS_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/operation/live_application_status";

    String LIVE_APPLICATION_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/operation/live_application";

    String PHONE_NUMBER_APPLICATION_STATUS_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/operation/phone_number_application_status";

    String PHONE_NUMBER_APPLICATION_URL = "https://open.microapp.bytedance.com/openapi/v1/microapp/operation/phone_number_application";

    /**
     * 查询短视频挂载能力申请状态
     * @return
     */
    OperationVideoApplicationStatusResponse videoApplicationStatus();

    /**
     * 申请「短视频挂载」能力
     * @return
     */
    OperationVideoApplicationResponse videoApplication(OperationVideoApplicationRequest request);


    /**
     * 查询「抖音直播组件」能力申请状态
     * @return
     */
    OperationLiveApplicationStatusResponse liveApplicationStatus();

    /**
     * 申请「抖音直播组件」能力
     * @return
     */
    OperationLiveApplicationResponse liveApplication(OperationLiveApplicationRequest request);

    /**
     * 查询「获取用户手机号」能力申请状态
     * @return
     */
    OperationPhoneNumberApplicationStatusResponse phoneNumberApplicationStatus();

    /**
     * 申请「获取用户手机号」能力
     * @return
     */
    OperationPhoneNumberApplicationResponse phoneNumberApplication(OperationPhoneNumberApplicationRequest request);

}
