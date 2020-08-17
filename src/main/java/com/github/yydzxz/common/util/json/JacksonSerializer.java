package com.github.yydzxz.common.util.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * jackson的驼峰转下划线策略有点奇怪
 * aBcDEFgH会被转为abc_defg_h,所以最好都通过JsonProperty注解明确指定属性对应的json别名
 *
 * @author yangyidian
 * @date 2020/08/03
 **/
public class JacksonSerializer implements JsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String toJson(Object object) {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonStr;
        try {
            jsonStr = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    @Override
    public <T> T parse(String jsonString, Class<T> clazz) {
        if(clazz == String.class){
            return (T)jsonString;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFieldAliasName(Field field) {
        String fieldAliasName = field.getName();
        Annotation annotation = field.getAnnotation(JsonAlias.class);
        if(annotation != null){
            JsonAlias jsonAnnotation = (JsonAlias)annotation;
            if(jsonAnnotation.value().length > 0 && !StrUtil.isEmpty(jsonAnnotation.value()[0])){
                fieldAliasName = jsonAnnotation.value()[0];
            }
        }else {
            annotation = field.getAnnotation(JsonProperty.class);
            if(annotation != null){
                JsonProperty jsonAnnotation = (JsonProperty)annotation;
                if(!StrUtil.isEmpty(jsonAnnotation.value())){
                    fieldAliasName = jsonAnnotation.value();
                }
            }
        }
        return fieldAliasName;
    }
}
