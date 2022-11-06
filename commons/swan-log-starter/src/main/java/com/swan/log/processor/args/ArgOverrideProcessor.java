package com.swan.log.processor.args;

import com.swan.log.config.SwanLogProperty;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

/** 参数超长处理器
 * @author zongf
 * @since 2022-11-07
 **/
public class ArgOverrideProcessor implements IArgProcessor, InitializingBean, Ordered {

    @Autowired
    private SwanLogProperty swanLogProperty;

    private Integer maxLength;

    private String overrideSuffix;

    @Override
    public Object process(Object argument) {
        if (argument == null) {
            return "null";
        }

        String arg = argument.toString();
        if (maxLength != null && arg.length() > maxLength) {
            arg = arg.substring(0, maxLength) + overrideSuffix;
        }
        return arg;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.maxLength = swanLogProperty.getArgMaxLength();
        this.overrideSuffix = swanLogProperty.getOverrideSuffix();
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
