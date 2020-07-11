package com.github.yydzxz.common.error;

import lombok.Getter;

/**
 * 错误码
 * https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#p3oIKr
 * @author yangyidian
 * @date 2020/06/28
 **/
@Getter
public enum ByteDanceMiniProgramErrorMsgEnum {


    CODE_40000(40000, "系统错误"),

    CODE_40001(40001, "参数错误"),

    CODE_40002(40002, "缺少参数"),

    CODE_40003(40003, "找不到相关第三方平台"),

    CODE_40004(40004, "找不到相关授权小程序"),

    CODE_40005(40005, "非法请求，当前没有用户登录"),

    CODE_40006(40006, "非法请求，请在第三方平台白名单IP地址内请求"),

    CODE_40007(40007, "非法请求，获取不到请求IP"),

    CODE_40008(40008, "非法请求，当前登录用户非法操作"),

    CODE_40009(40009, "第三方平台AccessToken已过期"),

    CODE_40010(40010, "第三方平台AccessToken不正确"),

    CODE_40011(40011, "第三方平台授权发起页域名未设置"),

    CODE_40012(40012, "第三方平台预授权码已过期"),

    CODE_40013(40013, "解析授权回调页域名错误"),

    CODE_40014(40014, "必须从第三方平台授权发起页域名跳转至本页面"),

    CODE_40015(40015, "解析第三方平台授权发起页域名错误"),

    CODE_40016(40016, "请确认授权入口页域名与授权回调后的域名相同，并且两者与创建第三方平台时填写的授权发起页的域名相同"),

    CODE_40017(40017, "解析授权入口页域名错误"),

    CODE_40018(40018, "授权码已过期"),

    CODE_40019(40019, "开发小程序或者已授权的小程序不能再授权给第三方平台"),

    CODE_40020(40020, "第三方平台授权小程序接口调用凭据已过期"),

    CODE_40021(40021, "第三方平台授权小程序接口刷新凭据已过期"),

    CODE_40022(40022, "此小程序未授权给第三方平台"),

    CODE_40023(40023, "域名不是第三方平台已设置的域名或子域名"),

    CODE_40024(40024, "域名数量超过限制"),

    CODE_40025(40025, "授权小程序是个人小程序，不支持调用设置业务域名接口"),

    CODE_40026(40026, "至少传递一个要被操作的域名类型"),

    CODE_40027(40027, "域名不能是IP地址且不支持端口"),

    CODE_40028(40028, "无法删除域名，请确认授权小程序已经添加了域名"),

    CODE_40029(40029, "找不到对应模版编号的模版"),
    ;

    private int code;
    private String msg;

    ByteDanceMiniProgramErrorMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过错误代码查找其中文含义.
     */
    public static String findMsgByCode(int code) {
        for (ByteDanceMiniProgramErrorMsgEnum value : ByteDanceMiniProgramErrorMsgEnum.values()) {
            if (value.code == code) {
                return value.msg;
            }
        }

        return null;
    }
}
