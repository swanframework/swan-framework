package com.swan.poi.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

public interface IExcelTemplate {

    /** 导出数据
     * @param data 数据
     * @param <T> 字段映射
     * @return
     */
    public <T> Workbook exportData(String sheetName, List<T> data, Class<T> clz);

    /** 导入数据
     * @param is 输入流
     * @param clz 每一行映射的数据类型
     * @param startRow 起始行编号
     * @return List
     */
    public <T> List<T> importData(InputStream is, Class<T> clz, Integer startRow);


}
