package com.github.yydzxz.common.util.json;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 问题太多，不推荐使用
 * https://segmentfault.com/a/1190000015634321
 * @author yangyidian
 * @date 2020/08/03
 **/
@Deprecated
public class FastJsonSerializer implements JsonSerializer {
    private static final SerializeConfig config = new SerializeConfig();
    static {
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    @Override
    public String toJson(Object object) {
        return JSON.toJSONString(object, config);
    }

    @Override
    public <T> T parse(String jsonString, Class<T> clazz){
        return JSON.parseObject(jsonString, clazz);
    }

    @Override
    public String getFieldAliasName(Field field) {
        String fieldAliasName = field.getName();
        Annotation annotation = field.getAnnotation(JSONField.class);
        if(annotation != null){
            JSONField serializedNameAnnotation = (JSONField)annotation;
            if(!StrUtil.isEmpty(serializedNameAnnotation.name())){
                fieldAliasName = serializedNameAnnotation.name();
            }
        }
        return fieldAliasName;
    }
}
