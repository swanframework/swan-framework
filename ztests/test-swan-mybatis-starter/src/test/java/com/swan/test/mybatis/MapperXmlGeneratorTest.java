package com.swan.test.mybatis;

import com.swan.mybatis.factory.EntityMetaInfoFactory;
import com.swan.mybatis.mapper.MapperMethodsMetaInfo;
import com.swan.mybatis.util.TemplateUtil;
import com.swan.mybatis.util.TxtFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongf
 * @date 2021-11-02
 */
public class MapperXmlGeneratorTest {

    private static final String BASE_MAPPER_FTL = "ftl/BaseMapper.ftl";

    private void createMapperXml(Class mapperInterface, Class entityType, String conditionName) {

        String namespace = mapperInterface.getName();
        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("namespace", namespace);
        dateMap.put("entityMeta", EntityMetaInfoFactory.createEntityMetaInfo(entityType, conditionName));
        dateMap.put("methodsInfo", new MapperMethodsMetaInfo(mapperInterface));

        String mapperXml = TemplateUtil.getContent(BASE_MAPPER_FTL, dateMap);

        String mapperPath = "test.xml";
        TxtFileUtil.writeFile(mapperPath , mapperXml);
    }

}
