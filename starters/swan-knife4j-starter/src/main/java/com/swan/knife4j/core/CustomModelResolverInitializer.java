package com.swan.knife4j.core;

import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/** 初始化自定义 ModelConverter
 * @author zongf
 * @since 2022-12-04
 **/
@Component
public class CustomModelResolverInitializer implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

        // 移除 ModelConverters 中初始化默认的 ModelConverter
        this.removeDefaultModelResolver();

        // 添加自定义的 ModelResolver
        ModelConverters.getInstance().addConverter(new CustomModelResolver());
    }

    private void removeDefaultModelResolver() {
        ModelResolver modelResolver = null;
        for (ModelConverter converter : ModelConverters.getInstance().getConverters()) {
            if (converter instanceof ModelResolver) {
                modelResolver = (ModelResolver) converter;
            }
        }
        ModelConverters.getInstance().removeConverter(modelResolver);
    }
}
