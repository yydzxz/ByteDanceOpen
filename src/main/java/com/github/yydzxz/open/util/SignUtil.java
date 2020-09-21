package com.github.yydzxz.open.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/09/21
 **/
@Slf4j
public class SignUtil {

    /**
     * 验签
     * @param msgSignature
     * @param tpToken
     * @param timestamp
     * @param nonce
     * @param encrypt
     * @return
     */
    public static boolean checkSignature(String msgSignature, String tpToken, String timestamp, String nonce, String encrypt) {
        try {
            String newMsgSignature = ServerVerification.getMsgSignature(tpToken, timestamp, nonce, encrypt);
            return newMsgSignature.equals(msgSignature);
        } catch (Exception e) {
            log.error("Checking signature failed, and the reason is :" + e.getMessage());
            log.error("msgSignature=[{}], tpToken=[{}], timestamp=[{}], nonce=[{}], encrypt=[{}]",
                msgSignature, tpToken, timestamp, nonce, encrypt);
            return false;
        }
    }
}
