package com.swan.core.listener;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 容器刷新广播器
 * @author zongf
 * @since 2021-11-26
 */
public class ApplicationRefreshMulticaster implements ApplicationListener<ContextRefreshedEvent> {

    // 是否已通知，防止重复触发
    private boolean hasNotified;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 防止重复触发
        if(hasNotified) {
            return;
        } else {
          hasNotified = true;
        }

        // 通知监听器
        if (event.getApplicationContext().getAutowireCapableBeanFactory() instanceof DefaultListableBeanFactory) {
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) event.getApplicationContext().getAutowireCapableBeanFactory();

            // 回调容器刷新监听器
            Map<String, ApplicationRefreshListener> beansMap = beanFactory.getBeansOfType(ApplicationRefreshListener.class);

            if (beansMap != null && !beansMap.isEmpty()) {

                List<ApplicationRefreshListener> listenerList = new ArrayList<>(beansMap.values());

                // 排序
                listenerList.sort(beanFactory.getDependencyComparator());

                // 回调
                listenerList.forEach(bean -> bean.onRefresh(beanFactory));
            }
        }
    }

}
