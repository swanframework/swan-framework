package com.swan.knife4j.core;

import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
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
    protected void applyBeanValidatorAnnotations(Schema property, Annotation[] annotations, Schema parent) {
        // 调用父类处理逻辑
        super.applyBeanValidatorAnnotations(property, annotations, parent);

        // 自定义扩展逻辑
        Map<String, Annotation> annos = new HashMap<>();
        if (annotations != null) {
            for (Annotation anno : annotations) {
                annos.put(anno.annotationType().getName(), anno);
            }
        }

        // 兼容处理 @Length 注解
        if (annos.containsKey("org.hibernate.validator.constraints.Length")) {
            if (property instanceof StringSchema) {
                Length pattern = (Length) annos.get("org.hibernate.validator.constraints.Length");
                StringSchema sp = (StringSchema) property;
                sp.minLength(Integer.valueOf(pattern.min()));
                sp.maxLength(Integer.valueOf(pattern.max()));
            }
        }

        // 处理 @Max 注解，包含边界值
        if (annos.containsKey("javax.validation.constraints.Max")) {
            if ("integer".equals(property.getType()) || "number".equals(property.getType())) {
                property.setExclusiveMaximum(true);
            }
        }

        // 处理 @Min 注解，包含边界值
        if (annos.containsKey("javax.validation.constraints.Min")) {
            if ("integer".equals(property.getType()) || "number".equals(property.getType())) {
                property.setExclusiveMinimum(true);
            }
        }
    }
}
