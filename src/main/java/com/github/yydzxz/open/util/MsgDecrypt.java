package com.github.yydzxz.open.util;

import com.alibaba.fastjson.JSON;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class MsgDecrypt {

    private Cipher cipher;
    public static int RANDOM_BYTES_POS = 32;

    public MsgDecrypt(String encodingAesKey) throws Exception {
        //AES key长度固定
        if (encodingAesKey.length() != 43) {
            throw new Exception("AES key 长度不合法");
        }
        byte[] aesKey = Base64.getDecoder().decode(encodingAesKey + "=");

        SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
        Cipher encCipher = Cipher.getInstance("AES/CBC/NoPadding");
        encCipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
    }

    //将四个byte解析为一个32位的数字，数字的值也就是消息体的String格式下的长度
    private int getLength(byte[] orderBytes) {
        ByteBuffer buf = ByteBuffer.wrap(orderBytes);
        buf.order(ByteOrder.BIG_ENDIAN);
        return buf.getInt();
    }

    private byte[] decode(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    public ByteDanceOpenMessage decrypt(String text) throws Exception {
        byte[] original;
        try {
            byte[] encrypted = Base64.getDecoder().decode(text);
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("解码异常");
        }

        String Content;
        String AppId;
        ByteDanceOpenMessage message;
        try {
            byte[] bytes = this.decode(original);

            //byte数组的第32、33、34、35个元素代表了消息体的真实字符个数，也就是长度
            byte[] pos = Arrays.copyOfRange(bytes, RANDOM_BYTES_POS, RANDOM_BYTES_POS + 4);
            int msgLength = getLength(pos);

            //根据解析出来的消息体长度值，截取真实的消息体
            Content = new String(Arrays.copyOfRange(bytes, RANDOM_BYTES_POS + 4, RANDOM_BYTES_POS + 4 + msgLength), StandardCharsets.UTF_8);
            //byte数组截去真实消息后，末尾剩下的字符就是appid
            AppId = new String(Arrays.copyOfRange(bytes, RANDOM_BYTES_POS + 4 + msgLength, bytes.length), StandardCharsets.UTF_8);

            message = JSON.parseObject(Content, ByteDanceOpenMessage.class);
            message.setFromTpAppId(AppId);
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Buffer异常");
        }
        return message;
    }

    public static void main(String[] args) throws Exception {
        MsgDecrypt test = new MsgDecrypt("1234567890123456789012345678901234567890qwe");
        test.decrypt("JymxvCwHjeN9hQvdN6uAOpq4QS2FmN3TOynFXfn/q8Ye8mcBHXCKC0/yRf/kwPFg4enZd30CuKjlfcJ23HStIPDe2HR9lbQ/9cgpPb0Iw/NFRFeFsBjYJKddlpSYGuUt3t78wv+vKLmLPxKB+4uGS0LPgprIGRiGomq0P2Pc8ZJkCJ/WoOkNUrD3zldKWIUuZPOeQXoMl2K//vGoH66c/GJd8KvICVjb90ZhUu7XaLU2+5vfPssh3CB23+/smGOEISwJZJOuftF+IFXSudQ49g==");
    }
}