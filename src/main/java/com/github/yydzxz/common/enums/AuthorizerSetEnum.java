package com.github.yydzxz.common.enums;

/**
 * @author yangyidian
 * @date 2021/03/12
 **/
public enum AuthorizerSetEnum {

    开发管理权限(1, "帮助小程序进行功能开发和开发设置"),
    基本信息设置权限(2, "帮助小程序进行设置名称、头像、简介、服务类目等信息"),
    运营管理权限(3, "帮助小程序管理搜索配置、客服消息等相关功能"),
    数据分析权限(4, "帮助小程序进行数据分析"),
    广告管理权限(5, "帮助小程序进行广告的投放和管理"),
    支付服务权限(6, "帮助小程序开通支付服务，获取支付相关信息"),
    流量主权限(7, "帮助开发者管理广告变现业务");

    private Integer id;

    private String description;

    AuthorizerSetEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
