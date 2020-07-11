package com.yyd.miniprogram;//package cn.ce.miniprograms.manager.bytedance.miniprogram;
//
//import cn.ce.miniprograms.manager.bytedance.common.service.IByteDanceService;
//import cn.ce.miniprograms.manager.bytedance.miniprogram.config.ByteDanceMiniProgramConfig;
//
///**
// * @author yangyidian
// * @date 2020/06/22
// **/
//public interface IByteDanceMiniProgramService extends IByteDanceService {
//    /**
//     * 获取access_token.
//     */
////    String GET_ACCESS_TOKEN_URL = "xxx";
//
//
//    /**
//     * <pre>
//     * 验证消息的确来自微信服务器.
//     * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
//     * </pre>
//     */
////    boolean checkSignature(String timestamp, String nonce, String signature);
//
//    /**
//     * 获取access_token, 不强制刷新access_token.
//     *
//     * @see #getAccessToken(boolean)
//     */
//    String getAccessToken();
//
//    /**
//     * <pre>
//     * 获取access_token，本方法线程安全.
//     * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
//     *
//     * 另：本service的所有方法都会在access_token过期是调用此方法
//     *
//     * 程序员在非必要情况下尽量不要主动调用此方法
//     *
//     * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
//     * </pre>
//     *
//     * @param forceRefresh 强制刷新
//     */
//    String getAccessToken(boolean forceRefresh);
//
//
////    /**
////     * <pre>
////     * Service没有实现某个API的时候，可以用这个，
////     * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
////     * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
////     * </pre>
////     *
////     * @param <E> .
////     * @param <T> .
////     * @param data 参数或请求数据
////     * @param executor 执行器
////     * @param uri 接口请求地址
////     * @return .
////     */
////    <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data);
//
//    /**
//     * <pre>
//     * 设置当字节跳动系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
//     * 默认：1000ms
//     * </pre>
//     *
//     * @param retrySleepMillis 重试等待毫秒数
//     */
//    void setRetrySleepMillis(int retrySleepMillis);
//
//    /**
//     * <pre>
//     * 设置当微信系统响应系统繁忙时，最大重试次数.
//     * 默认：5次
//     * </pre>
//     *
//     * @param maxRetryTimes 最大重试次数
//     */
//    void setMaxRetryTimes(int maxRetryTimes);
//
//    /**
//     * 获取ByteDanceMiniProgramConfig 对象.
//     *
//     * @return ByteDanceMiniProgramConfig
//     */
//    ByteDanceMiniProgramConfig getByteDanceMiniProgramConfig();
//
//    /**
//     * 注入 {@link ByteDanceMiniProgramConfig} 的实现.
//     *
//     * @param byteDanceMiniProgramConfig
//     */
//    void setByteDanceMiniProgramConfig(ByteDanceMiniProgramConfig byteDanceMiniProgramConfig);
//
//
////    /**
////     * 返回二维码相关接口方法的实现类对象，以方便调用其各个接口.
////     *
////     * @return WxMaQrcodeService
////     */
////    IByteDanceMiniProgramQrcodeService getByteDanceMiniProgramService();
////
////
////    /**
////     * 返回代码操作相关的 API.
////     *
////     * @return ByteDanceMiniProgramCodeService
////     */
////    IByteDanceMiniProgramCodeService getByteDanceMiniProgramCodeService();
//
//
//}
