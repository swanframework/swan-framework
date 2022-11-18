package com.swan.mybatis.core;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.InterceptorChain;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/** 核心逻辑
 * @author zongf
 * @date 2021-01-08
 */
@Slf4j
public class BaseMapperRegister implements ApplicationListener<ContextRefreshedEvent> {

    // 标识是否已解析
    private static boolean parsed = false;

    private DefaultListableBeanFactory beanFactory ;

    /** 注册字段拦截器, 借助反射向mybtis 主配置类中注册字段拦截器
     * @param sqlSessionFactory
     * @author zongf
     * @date 2021-01-08
     */
    public void registerInterceptor(SqlSessionFactory sqlSessionFactory) {
        // 借助反射向mybatis 中注册BaseMapper 拦截器
        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            Field chainField = configuration.getClass().getDeclaredField("interceptorChain");
            chainField.setAccessible(true);
            InterceptorChain interceptorChain = (InterceptorChain) chainField.get(configuration);

            Field interceptors = interceptorChain.getClass().getDeclaredField("interceptors");
            interceptors.setAccessible(true);
            List list = (List) interceptors.get(interceptorChain);

            // 添加字段拦截器，完成 @AutoTime, @Encrypt 等字段自动赋值
            list.add(0, new EntityFieldInterceptor());
            // 添加 pageHelper 插件
            list.add(1, new PageInterceptor());
        } catch (Exception ex) {
            throw new RuntimeException("Mybatis 字段拦截器注册失败!", ex);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        beanFactory = (DefaultListableBeanFactory) event.getApplicationContext().getAutowireCapableBeanFactory();

        if(parsed){
            log.info("重复处理....");
            return;
        }

        // 为BaseMapper自动生成MapperStatement
        Set<Class> entityClasses = new BaseMapperStatementCreator(beanFactory).process();

        // 字段拦截器处理
        new EntityFieldParser().parseFields(entityClasses);

        // 注册字段拦截器
        registerInterceptor(beanFactory.getBean(SqlSessionFactory.class));

        // 防止重复解析
        parsed = true;

    }
}
