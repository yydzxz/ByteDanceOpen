package com.github.yydzxz.common.util.json;

import java.lang.reflect.Field;

/**
 * @author yangyidian
 * @date 2020/08/03
 **/
public interface JsonSerializer {

    /**
     * 对象转json字符串
     * @param object
     * @return
     */
    String toJson(Object object);

    /**
     * json字符串转对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T parse(String jsonString, Class<T> clazz);

    /**
     * 获取对象字段的别名
     * 通常是用注解来标明别名，所以一般是通过注解获取别名
     * @param field
     * @return
     */
    String getFieldAliasName(Field field);

}
