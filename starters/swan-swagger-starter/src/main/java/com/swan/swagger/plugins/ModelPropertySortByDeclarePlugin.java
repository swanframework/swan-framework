package com.swan.swagger.plugins;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.Field;

import static springfox.documentation.schema.Annotations.findPropertyAnnotation;
import static springfox.documentation.swagger.schema.ApiModelProperties.findApiModePropertyAnnotation;

/** 模型属性按声明顺序排序
 * @author zongf
 * @date 2021-07-21
 */
@Slf4j
public class ModelPropertySortByDeclarePlugin implements ModelPropertyBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<BeanPropertyDefinition> beanPropertyDefinitionOpt = context.getBeanPropertyDefinition();
        Optional<ApiModelProperty> annotation = Optional.absent();
        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(findPropertyAnnotation(context.getBeanPropertyDefinition().get(), ApiModelProperty.class));
        }
        if (beanPropertyDefinitionOpt.isPresent()) {
            BeanPropertyDefinition beanPropertyDefinition = beanPropertyDefinitionOpt.get();
            if (annotation.isPresent() && annotation.get().position() != 0) {
                return;
            }
            AnnotatedField field = beanPropertyDefinition.getField();
            Class<?> clazz = field.getDeclaringClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            Field declaredField;
            try {
                declaredField = clazz.getDeclaredField(field.getName());
            } catch (NoSuchFieldException | SecurityException e) {
                log.error("", e);
                return;
            }
            int indexOf = indexOf(declaredFields, declaredField);
            if (indexOf != -1) {
                context.getBuilder().position(indexOf);
            }
        }
    }

    public Integer indexOf(Field[] fields, Field target) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

}
