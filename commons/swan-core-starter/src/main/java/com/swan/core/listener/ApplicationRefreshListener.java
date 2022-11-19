package com.swan.core.listener;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/** 通知容器刷新, 支持 @Order 进行排序
 * @author zongf
 * @since 2021-01-11
 */
public interface ApplicationRefreshListener {

    /** 监听容器刷新完成，此时非懒加载的单实例bean的初始化已完成
     * @param beanFactory
     * @author zongf
     * @since 2021-01-11
     */
    public void onRefresh(DefaultListableBeanFactory beanFactory);

}
