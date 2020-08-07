package com.github.yydzxz.common.util.json;

import java.lang.reflect.Field;

/**
 * @author yangyidian
 * @date 2020/08/03
 **/
public interface JsonSerializer {

    String toJson(Object object);

    <T> T parse(String jsonString, Class<T> clazz);

    /**
     * 获取对象字段的别名
     * 通常是用注解来标明别名，所以一般是通过注解获取别名
     * @param field
     * @return
     */
    String getFieldAliasName(Field field);

}
