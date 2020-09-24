package com.github.yydzxz.open.util;

import org.junit.jupiter.api.Test;

class ServerVerificationTest {


    @Test
    public void getMsgSignature() throws Exception {
        String msgSignature = "XXX";
        String newMsgSignature = ServerVerification.getMsgSignature("XXX", "XXX", "XXX", "XXX");
        boolean res = msgSignature.equals(newMsgSignature);
        System.out.println(res);
    }
}