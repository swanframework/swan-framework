package com.swan.mybatis.core;

import com.swan.freemarker.core.IFreemarkerTemplate;
import com.swan.mybatis.mapper.field.meta.EntityMetaInfo;
import com.swan.core.utils.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import com.swan.mybatis.config.SwanMybatisProperties;
import com.swan.mybatis.factory.EntityMetaInfoFactory;
import com.swan.mybatis.mapper.MapperMethodsMetaInfo;
import com.swan.mybatis.mapper.methods.BaseMethod;
import com.swan.core.utils.TxtFileUtil;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;

/** BaseMapper接口方法映射MapperStatement生成器
 * @author zongf
 * @since 2021-01-08
 */
@Slf4j
public class AutoMapperStatementRegister {

    // mybatis 主配置类
    private Configuration configuration;

    // spring bean 工厂
    private DefaultListableBeanFactory applicationContext;

    // 模板路径
    private static final String BASE_MAPPER_FTL = "mybatis/BaseMapper.ftl";

    // 自定义配置信息
    private SwanMybatisProperties swanMybatisProperties;

    // freemarker 模板
    private IFreemarkerTemplate freemarkerTemplate;

    public AutoMapperStatementRegister(DefaultListableBeanFactory beanFactory) {
        this.applicationContext = beanFactory;
        this.configuration = this.applicationContext.getBean(SqlSessionFactory.class).getConfiguration();
        this.swanMybatisProperties = this.applicationContext.getBean(SwanMybatisProperties.class);

        this.freemarkerTemplate = this.applicationContext.getBean("freemarkerTemplateInside", IFreemarkerTemplate.class);
    }

    public Set<Class> process() {

        Set<Class> entityClass = new HashSet<>();

        // 获取所有继承IBaseMapper 接口, 且使用@Mapper 修饰的组件.
        // Mybatis 会为每个@Mapper 修饰的接口注册MapperFactoryBean 组件
        List<MapperFactoryBean> factoryBeanList = getBaseMapperFactoryBeans();

        for (MapperFactoryBean mapperFactoryBean : factoryBeanList) {

            // 获取 MapperFactoryBean 真正的类型
            Class mapperInterface = mapperFactoryBean.getMapperInterface();

            // 只处理使用 @Mapper 修饰的接口
            if (!mapperInterface.isAnnotationPresent(Mapper.class)) {
                continue;
            }

            // 获取IBaseMapper 的2个参数类型
            Type[] rawParamTypes = ReflectUtil.getRawParamTypes(mapperInterface, BaseMethod.class);

            // 获取实体类型
            Class entityClz = (Class) rawParamTypes[1];

            // 生成动态xml
            String mapperXml = createMapperXml(mapperInterface, entityClz);
            // 注册 mapperXml, 交由mybatis 解析
            registerMapperXml(mapperInterface.getName(), mapperXml);

            // 记录mapper 的实体类型
            entityClass.add(entityClz);
        }

        return entityClass;
    }

    /** 获取所有继承IBaseMapper 接口, 且使用@Mapper 修饰的组件.
     * @return
     * @author zongf
     * @since 2021-01-11
     */
    private List<MapperFactoryBean> getBaseMapperFactoryBeans() {
        List<MapperFactoryBean> baseMapperFactoryBeanList = new ArrayList<>();

        // mybatis 会将所有@Mapper 修饰的接口, 注册为MapperFactoryBean 定义信息
        Map<String, MapperFactoryBean> beanMap = this.applicationContext.getBeansOfType(MapperFactoryBean.class);

        for (MapperFactoryBean mapperFactoryBean : beanMap.values()) {

            // 获取 MapperFactoryBean 真正的类型
            Class mapperInterface = mapperFactoryBean.getMapperInterface();

            // 如果实现了 IBaseMapper.class 接口
            if (ReflectUtil.isImplement(mapperInterface, BaseMethod.class)) {
                baseMapperFactoryBeanList.add(mapperFactoryBean);
            }
        }

        return baseMapperFactoryBeanList;
    }

    /** 动态生成xml 映射文件
     * @param mapperInterface 接口类型
     * @param entityType 实体类型
     * @return String
     * @author zongf
     * @since 2020-01-09
     */
    private String createMapperXml(Class mapperInterface, Class entityType) {

        // 解析实体字段信息
        EntityMetaInfo entityMetaInfo = EntityMetaInfoFactory.createEntityMetaInfo(entityType, swanMybatisProperties.getIgnoreFields());

        // 解析需要生成的方法列表
        MapperMethodsMetaInfo methodsInfo = new MapperMethodsMetaInfo(mapperInterface);

        String namespace = mapperInterface.getName();
        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("namespace", namespace);            // 命名空间
        dateMap.put("entityMeta", entityMetaInfo);      // 字段信息
        dateMap.put("methodsInfo", methodsInfo);        // 需要生成的方法列表

        String mapperXml = this.freemarkerTemplate.getContent(BASE_MAPPER_FTL, dateMap);

        // 是否需要将生成的 xml 配置文件，写入文件
        if (this.swanMybatisProperties != null && swanMybatisProperties.getLogMapper().isEnable()) {
            SwanMybatisProperties.LogMapper logMapper = this.swanMybatisProperties.getLogMapper();
            int idx = namespace.lastIndexOf(".");
            String simpleMapper = namespace.substring(idx + 1, namespace.length());
            if (logMapper.getMappers().contains(simpleMapper)) {
                String mapperPath = StringUtils.hasText(logMapper.getPath()) ? logMapper.getPath() + "/" : "";
                mapperPath += namespace + ".xml";
                TxtFileUtil.writeFile(mapperPath , mapperXml);
                log.info("动态生成 Mapper 映射文件: {}", mapperPath);
            }
        }
        return mapperXml;
    }

    /** 解析xml文件
     * @param id
     * @param mapperXml
     */
    private void registerMapperXml(String id, String mapperXml) {
        // mybatis 会根据资源id判断是否已经解析过此xml. 这样可防止用户创建相同的资源
        String resourceId = id + "-auto";
        InputStream inputStream = new ByteArrayInputStream(mapperXml.getBytes());
        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resourceId, configuration.getSqlFragments());
        mapperParser.parse();
    }


    /** 获取单表查询条件condition 的xml名称.
     *  如果
     * @param conditionType
     * @return
     */
    @Deprecated
    private String getConditionSqlFragmentName(Class conditionType) {
        Map<String, XNode> sqlFragments = configuration.getSqlFragments();

        // 按condition的简单类名,搜索是否已自定义xml片段
        boolean hasCustomCondition = false;
        String customConditionName = conditionType.getCanonicalName() + ".condition";
        for (String sqlId : sqlFragments.keySet()) {
            if (sqlId.equals(customConditionName)) {
                hasCustomCondition = true;
                break;
            }
        }

        // 如果未自定义condition, 则使用自动生成的xml中的空的<sql id="WhereCondition"></sql>.
        // 如果找到多个自定义的,则抛出异常
        if (hasCustomCondition) {
            return customConditionName;
        }else {
            return "WhereCondition";
        }
    }


}