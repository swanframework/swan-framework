package com.swan.poi.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

public interface IExcelService {

    /** 生成 excel
     * @param data 数据
     * @param <T> 字段映射
     * @return
     */
    public <T> Workbook write(String sheetName, List<T> data, Class<T> clz);

    /** 解析 excel 为 List
     * @param is
     * @param clz
     * @param <T>
     * @return
     */
    public <T> List<T> read(InputStream is, Class<T> clz);


}
