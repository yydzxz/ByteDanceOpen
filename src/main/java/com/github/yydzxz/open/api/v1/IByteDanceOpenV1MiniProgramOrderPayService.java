package com.github.yydzxz.open.api.v1;

import com.github.yydzxz.open.api.v1.request.orderpay.*;
import com.github.yydzxz.open.api.v1.response.orderpay.*;

/**
 * 小程序担保支付相关
 * @author kbigbus
 * @date 2021/06/21
 **/
public interface IByteDanceOpenV1MiniProgramOrderPayService {

    /**
     * 服务端预下单
     */
    String CREATE_ORDER_URL = "https://developer.toutiao.com/api/apps/ecpay/v1/create_order";

    /**
     * 订单查询
     */
    String QUERY_ORDER_URL ="https://developer.toutiao.com/api/apps/ecpay/v1/query_order";

    /**
     * 退款请求
     */
    String CREATE_REFUND_URL ="https://developer.toutiao.com/api/apps/ecpay/v1/create_refund";

    /**
     * 查询退款
     */
    String QUERY_REFUND_URL ="https://developer.toutiao.com/api/apps/ecpay/v1/query_refund";

    /**
     * 分账请求
     */
    String SETTLE_URL ="https://developer.toutiao.com/api/apps/ecpay/v1/settle";

    /**
     * 查询分账
     */
    String QUERY_SETTLE_URL ="https://developer.toutiao.com/api/apps/ecpay/v1/query_settle";

    /**
     * 服务商进件
     */
    String ADD_MERCHANT_URL ="https://developer.toutiao.com/api/apps/ecpay/saas/add_merchant";

    /**
     * 分账方进件
     */
    String ADD_SUB_MERCHANT_URL= "https://developer.toutiao.com/api/apps/ecpay/saas/add_sub_merchant";

    /**
     * 服务商为授权小程序进件
     */
    String GET_APP_MERCHANT_URL = "https://developer.toutiao.com/api/apps/ecpay/saas/get_app_merchant";



    CreateOrderResponse createOrder(CreateOrderRequest request);

    QueryOrderResponse queryOrder(QueryOrderRequest request);

    CreateRefundResponse createRefund(CreateRefundRequest request);

    QueryRefundResponse queryRefund(QueryRefundRequest request);

    SettleResponse settle(SettleRequest request);

    QuerySettleResponse querySettle(QuerySettleRequest request);

    AddMerchantResponse addMerchant(AddMerchantRequest request);

    AddSubMerchantResponse addSubMerchant(AddSubMerchantRequest request);

    GetAppMerchantResponse getAppMerchant(GetAppMerchantRequest request);


}
