package com.swan.knife4j.core;

import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zongf
 * @since 2022-12-04
 **/
public class CustomModelResolver extends ModelResolver {

    public CustomModelResolver() {
        super(Json.mapper());
    }

    @Override
    protected void applyBeanValidatorAnnotations(Schema schema, Annotation[] annotations, Schema parent) {
        // 调用父类处理逻辑
        super.applyBeanValidatorAnnotations(schema, annotations, parent);

        // 自定义扩展逻辑
        Map<String, Annotation> annos = new HashMap<>();
        if (annotations != null) {
            for (Annotation anno : annotations) {
                annos.put(anno.annotationType().getName(), anno);
            }
        }

        // 兼容处理 @Length 注解
        if (annos.containsKey("org.hibernate.validator.constraints.Length")) {
            if (schema instanceof StringSchema) {
                Length pattern = (Length) annos.get("org.hibernate.validator.constraints.Length");
                StringSchema sp = (StringSchema) schema;
                sp.minLength(Integer.valueOf(pattern.min()));
                sp.maxLength(Integer.valueOf(pattern.max()));
            }
        }
    }
}
