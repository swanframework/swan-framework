package com.swan.generator.config;

import com.swan.generator.generator.EntityMetaCreator;
import com.swan.generator.persistence.dao.IMetaDao;
import com.swan.generator.persistence.dao.MetaDao;
import com.swan.generator.service.api.IMybatisGeneratorService;
import com.swan.generator.service.impl.MybatisGeneratorService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/** 自动化配置，向spring中注册bean
 * @author zongf
 * @date 2021-03-01
 */
@Configuration
@Import(MetaDao.class)
@EnableConfigurationProperties(SbootGeneratorMybatisProperties.class)
public class AutoConfigCodesGenerator {

    @Bean
    public IMybatisGeneratorService mybatisGenerator(){
        return new MybatisGeneratorService();
    }

    @Bean
    public IMetaDao metaDao(){
        return new MetaDao();
    }

    @Bean
    public EntityMetaCreator entityMetaCreator() {
        return new EntityMetaCreator();
    }


}
