package com.github.yydzxz.common.enums;

/**
 * @author yangyidian
 * @date 2021/03/12
 **/
public enum ModelNameEnum {

    porn("图片涉黄"),
    cartoon_leader("领导人漫画"),
    anniversary_flag("特殊标志"),
    sensitive_flag("敏感旗帜"),
    sensitive_text("敏感文字"),
    leader_recognition("敏感人物"),
    bloody("图片血腥"),
    fandongtaibiao("未准入台标"),
    plant_ppx("图片涉毒"),
    high_risk_social_event("社会事件"),
    high_risk_boom("爆炸"),
    high_risk_money("人民币"),
    high_risk_terrorist_uniform("极端服饰"),
    high_risk_sensitive_map("敏感地图"),
    great_hall("大会堂"),
    cartoon_porn("色情动漫"),
    party_founding_memorial("建党纪念");

    private String descripion;

    ModelNameEnum(String descripion) {
        this.descripion = descripion;
    }
}
