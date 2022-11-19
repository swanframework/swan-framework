package com.swan.core.utils;

/**
 * @author zongf
 * @since 2021-03-01
 */
public class NameUtil {

    /** 将匈牙利命名改为驼峰命名
     * @param hungaryName 匈牙利命名
     * @return String
     * @author zongf
     * @since 2019-11-30
     */
    public static String toHumpName(String hungaryName) {

        // 为空直接返回
        if (hungaryName == null || hungaryName.isEmpty()) {
            return hungaryName;
        }

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

    /** 驼峰命名转换为匈牙利命名
     * @param humpName
     * @return
     */
    public static String toHungaryName(String humpName) {
        // 为空直接返回
        if (humpName == null || humpName.isEmpty()) {
            return humpName;
        }

        // Ascii 码距离
        int gap = 'a'-'A';

        StringBuffer sb = new StringBuffer();
        for (char ch : humpName.toCharArray()) {
            if('A'<=ch && ch<='Z'){
                char temp = (char)(ch+gap);
                sb.append("_"+temp);
            }else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    /** 首字母大写
     * @param name
     * @return String
     * @author zongf
     * @since 2021-03-01
     */
    public static String firstUppercase(String name) {
        if (name == null) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }


    public static void main(String[] args) {
        System.out.println(toHungaryName("age"));
        System.out.println(toHungaryName("aUserName"));
    }

}
