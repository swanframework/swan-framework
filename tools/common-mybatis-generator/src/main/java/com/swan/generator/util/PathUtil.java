package com.swan.generator.util;

import java.io.File;

/** 路径工具类
 * @author zongf
 * @since 2021-03-25
 */
public class PathUtil {

    /** 获取 java 文件绝对路径
     * @return String
     * @author zongf
     * @since 2021-03-01
     */
    public static String getClassPath(String projectPath, String packageName, String fileName) {
        return getPath(projectPath, packageName, fileName, "src/main/java",".java");
    }

    /** 获取 xml 文件绝对路径
     * @return String
     * @author zongf
     * @since 2021-03-01
     */
    public static String getXmlPath(String projectPath, String packageName, String fileName) {
        return getPath(projectPath, packageName, fileName, "src/main/resources", ".xml");
    }

    public static String getPath(String projectPath, String packageName, String fileName, String mainRelativePath, String suffix) {

        // 项目目录
        if (projectPath == null) {
            String newFilePath = new File("tmp").getAbsolutePath();
            int idx = newFilePath.lastIndexOf("/");
            projectPath = newFilePath.substring(0, idx);
        }

        StringBuffer sb = new StringBuffer();
        sb.append(projectPath)
                .append("/").append(mainRelativePath)
                .append("/").append(packageName.replaceAll("\\.", "/"))
                .append("/").append(fileName)
                .append(suffix);
        return sb.toString();
    }


}
