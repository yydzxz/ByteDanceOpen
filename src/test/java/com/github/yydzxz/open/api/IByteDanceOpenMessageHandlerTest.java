package com.github.yydzxz.open.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.yydzxz.open.message.handler.LogHandler;
import org.junit.jupiter.api.Test;

class IByteDanceOpenMessageHandlerTest {

    @Test
    void handle() {

    }

    @Test
    void getHandlerName() {
        assertEquals("LogHandler", new LogHandler().getHandlerName());
    }
}