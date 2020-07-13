//package com.yyd.open.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.IntBuffer;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 根据字节跳动提供的php demo改写的java版本
// * @author yangyidian
// * @date 2020/06/22
// **/
//@Slf4j
//public class ByteDanceBase64 {
//    public static int blockSize = 32;
//
//    private static Charset charset = StandardCharsets.ISO_8859_1;
//
////    public static String decode(String encodeAesKey, String encryptMsg){
////        // get aes key
////        byte[] AESKey = Base64.getDecoder().decode(encodeAesKey + "=");
////        // decrypt msg
////        String decryptMsg = null;
////        try {
////            decryptMsg = decrypt(encryptMsg, new String(AESKey));
////        } catch (Exception e) {
////            log.error(e.getMessage(), e);
////            log.error("encodeAesKey: {}", encodeAesKey);
////            log.error("encryptMsg: {}", encryptMsg);
////            throw new RuntimeException("解密失败");
////        }
////        // plain text
////        log.info("ByteDanceBase64#decode: {}", decryptMsg);
////        return decryptMsg;
////    }
//
////    public static String decryptMsg(String rawData, String key) throws Exception {
////        byte[] ASEKey = Base64.getDecoder().decode(key + "=");
////        log.info("ASEKey len: {}", ASEKey.length );
////        log.info("ASEKey: {}", new String(ASEKey, StandardCharsets.ISO_8859_1) );
////
////        String decrypted =  AESCBCDecrypt(rawData, new String(ASEKey, StandardCharsets.ISO_8859_1));
////        String result = decode(decrypted);
////        if (result.length() < 16) {
////            throw new RuntimeException("AesEncryptUtil AES解密串非法，小于16位");
////        }
////        // 去除16位随机字符串
////        String content = result.substring(16);
////
////
////        // 获取消息体长度
////        //php中这一步content.substring(16, 20).getBytes()，得到的结果是什么？
////        int[] lengthList = unpackNStar(content.substring(16, 20).getBytes(StandardCharsets.ISO_8859_1));
////        int postBodyLen = lengthList[0];
////
////
////        // 获取消息体
////        String postBodyMsg = content.substring(20, 20 + postBodyLen);
////
////        // 获取消息体的第三方平台 appid
////        String fromTpAppId = content.substring(20 + postBodyLen);
////
////        ByteDanceOpenMessage message = new JSONObject().toBean(ByteDanceOpenMessage.class);
////        message.setFromTpAppId(fromTpAppId);
////
////    }
//
//    public static String AESCBCDecrypt(String encryptData, String ASEKey) throws Exception {
//        try {
//
//            byte[] decodedText = Base64.getDecoder().decode(encryptData);
//            IvParameterSpec ivspec = new IvParameterSpec(decodedText, 0, 16);
//            SecretKeySpec keyspec = new SecretKeySpec(ASEKey.getBytes(StandardCharsets.ISO_8859_1), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//            byte[] decrypted = cipher.doFinal(decodedText);
//            String str = new String(decrypted, StandardCharsets.ISO_8859_1);
//            return str;
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//            log.error("encryptData: {}", encryptData);
//            throw e;
//        }
//    }
//
//
//    public static void main(String[] args) throws Exception {
////        String decrypted = decryptMsg(
////            "HDOfPQdliuopy1mN/vKr9OjQf0Ofm1Uk72B3jFrybhGGxyTawZK6tZXSCZQmd2E5lurRAUG9LhSI7Wn54iz+NaD7ZI0hkXhc1b0IZ+qHTfsZJgnPdD3fVnARzldX3dQocZjiE3TXdLXHjrbj9u+4FgZ/y1g1KJFGUst1YdJp8uWds4X1db0gBU0GEiDH6iiqaL2vNo9d+sKUZcfcHflSjrPOZnwpEEDaRVIMCn/vqWTKr6C989nld3ptiVlf9jiH7swMFdX7zUF9xvRkcXQ/ag==", "1234567890123456789012345678901234567890qwe");
////
////
////        String result = decode(decrypted);
////        if (result.length() < 16) {
////            throw new RuntimeException("AesEncryptUtil AES解密串非法，小于16位");
////        }
////        // 去除16位随机字符串
////        String content = result.substring(16);
////        log.info("---------------");
////        log.info(content);
////        log.info("---------------");
////
////        // 获取消息体长度
////        //php中这一步content.substring(16, 20).getBytes()，得到的结果是什么？
////        int[] lengthList = unpackNStar(content.substring(16, 20).getBytes(StandardCharsets.ISO_8859_1));
////        int postBodyLen = lengthList[0];
////        log.info("---------------");
////        log.info(postBodyLen + "");
////        log.info("---------------");
////
////        // 获取消息体
////        String postBodyMsg = content.substring(20, 20 + postBodyLen);
////        log.info("---------------");
////        log.info(postBodyMsg + "");
////        log.info("---------------");
////
////        // 获取消息体的第三方平台 appid
////        String fromTpAppId = content.substring(20 + postBodyLen);
////        log.info("---------------");
////        log.info(fromTpAppId + "");
////        log.info("---------------");
//
//    }
//
//    public static int[] unpackNStar ( byte[] bytes ) {
//        // first, wrap the input array in a ByteBuffer:
//        ByteBuffer byteBuf = ByteBuffer.wrap( bytes );
//
//        // then turn it into an IntBuffer, using big-endian ("Network") byte order:
//        byteBuf.order( ByteOrder.BIG_ENDIAN );
//        IntBuffer intBuf = byteBuf.asIntBuffer();
//
//        // finally, dump the contents of the IntBuffer into an array
//        int[] integers = new int[ intBuf.remaining() ];
//        intBuf.get( integers );
//        return integers;
//    }
//
//    public static int unpackNStar2(byte[] bytes) throws IOException {
//        ByteBuffer byteBuf = ByteBuffer.wrap( bytes );
//        byteBuf.order( ByteOrder.BIG_ENDIAN );
//        byte[] dest = new byte[4];
//        byteBuf.get(dest, 0, 4);
//        InputStream bis = new ByteArrayInputStream(dest);
//        DataInputStream dataInputStream = new DataInputStream(bis);
//        return dataInputStream.readInt();
//    }
//
//
//
//    public static String decode(String text)
//    {
//        int pad = ord(text.substring(text.length() - 1));
//
//        if (pad < 1 || pad > blockSize) {
//            pad = 0;
//        }
//        return text.substring(0, text.length() - pad);
//    }
//
//    public static int ord(String s) {
//        return s.length() > 0 ? (s.getBytes(StandardCharsets.UTF_8)[0] & 0xff) : 0;
//    }
//
//}
