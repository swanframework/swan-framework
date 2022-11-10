package com.swan.freemarker.core;

import java.util.Map;

/** Freemarker 工具类
 * @author zongf
 * @since 2022-11-10
 **/
public interface IFreemarkerTemplate {

    /** 获取模板数据
     * @param ftlRelativePath ftl 模板相对位置
     * @param root 根对象集合
     * @return String
     * @author zongf
     * @since 2022-11-10
     */
    String getContent(String ftlRelativePath, Map<String, Object> root);

    /** 获取模板数据
     * @param ftlRelativePath ftl 模板相对位置
     * @param rootName 根对象名称
     * @param rootValue 根对象
     * @return String
     * @author zongf
     * @since 2022-11-10
     */
    String getContent(String ftlRelativePath, String rootName, Object rootValue);

}
