package com.github.yydzxz.common.util.json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JacksonSerializerTest {

    private JsonSerializer jsonSerializer;

    @BeforeEach
    void setUp() {
        if(jsonSerializer == null){
            jsonSerializer = new JacksonSerializer();
        }
    }

    @Test
    void toJson() {
        JsonSerializer jsonSerializer = new JacksonSerializer();
        JsonTestClass testObject = new JsonTestClass();
        testObject.setABcDEFgH("value");
        testObject.setAbcdEfG("value");
        String jsonString = jsonSerializer.toJson(testObject);
        assertEquals("{\"aaaaa\":\"value\",\"bbbbb\":\"value\"}", jsonString);
    }

    @Test
    void parse() {
        JsonTestClass testClass = jsonSerializer.parse("{\"aaaaa\":\"value\",\"bbbbb\":\"value\"}", JsonTestClass.class);
        assertEquals("value", testClass.getAbcdEfG());
    }

    @Test
    void parse2() {
        jsonSerializer.parse("{\"aaaaa\":\"value\",\"bbbbb\":\"value\"}", String.class);
    }

    @Test
    void getFieldAliasName() {
    }
}