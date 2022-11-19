package com.swan.generator.util;

/**
 * @author zongf
 * @date 2021-03-01
 */
public class NameUtil {

    /** 将匈牙利命名改为驼峰命名
     * @param hungaryName 匈牙利命名
     * @return String
     * @author zongf
     * @date 2019-11-30
     */
    public static String toHumpName(String hungaryName) {

        // 如果字段不包含下划线，则直接返回字段名
        if(!hungaryName.contains("_")) return hungaryName;

        // 包含_, 则转换
        String[] nameArray = hungaryName.split("_");
        StringBuffer nameSb = new StringBuffer(nameArray[0]);
        for (int i = 1; i < nameArray.length; i++) {
            String name = firstUppercase(nameArray[i]);
            nameSb.append(name);
        }

        return nameSb.toString();
    }

    /** 首字母大写
     * @param name
     * @return String
     * @author zongf
     * @date 2021-03-01
     */
    public static String firstUppercase(String name) {
        if (name == null) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }


}
