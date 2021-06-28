package com.github.yydzxz.open.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    /**
     * 生成下单sign
     */
    public static String getSign(Map<String, Object> paramsMap, String salt) {
        List<String> paramsArr = new ArrayList<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            if (key.equals("other_settle_params")) {
                continue;
            }
            String value = entry.getValue().toString();

            value = value.trim();
            if (value.startsWith("\"") && value.endsWith("\"") && value.length() > 1) {
                value = value.substring(1, value.length() - 1);
            }
            value = value.trim();
            if (value.equals("") || value.equals("null")) {
                continue;
            }
            switch (key) {
                case "app_id":
                case "thirdparty_id":
                case "sign":
                    break;
                default:
                    paramsArr.add(value);
                    break;
            }
        }
        paramsArr.add(salt);
        Collections.sort(paramsArr);
        StringBuilder signStr = new StringBuilder();
        String sep = "";
        for (String s : paramsArr) {
            signStr.append(sep).append(s);
            sep = "&";
        }
        return md5FromStr(signStr.toString());
    }

    public static String md5FromStr(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
