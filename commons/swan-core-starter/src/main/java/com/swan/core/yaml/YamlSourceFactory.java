package com.swan.core.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

/*** yml 格式属性文件解析
 * @author zongf
 * @since 2021-12-01
 */
public class YamlSourceFactory extends DefaultPropertySourceFactory {

    private static YamlPropertiesFactoryBean yamlFactoryBean = new YamlPropertiesFactoryBean();

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String sourceName = (name == null) ? resource.getResource().getFilename() : name;
        if (sourceName != null) {
            yamlFactoryBean.setResources(resource.getResource());
            yamlFactoryBean.afterPropertiesSet();
            return new PropertiesPropertySource(sourceName, yamlFactoryBean.getObject());
        }
        return new ResourcePropertySource(sourceName, resource);
    }
}