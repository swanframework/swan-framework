package com.swan.poi.cache;

import com.swan.core.utils.ReflectUtil;
import com.swan.poi.anno.ExcelColumn;
import com.swan.poi.domain.ExcelColumnInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author zongf
 * @since 2023-05-16
 **/
public class ExcelColumnCache {

    public static Map<Class, List<ExcelColumnInfo>> cache = new ConcurrentHashMap<>();


    public static List<ExcelColumnInfo> get(Class clz) {

        List<ExcelColumnInfo> excelColumnInfos = cache.get(clz);

        if (excelColumnInfos == null) {
            synchronized (clz) {
                excelColumnInfos = cache.get(clz);

                if (excelColumnInfos == null) {
                    excelColumnInfos = parseClass(clz);
                    cache.put(clz, excelColumnInfos);
                }
            }
        }

        return excelColumnInfos;
    }

    private static List<ExcelColumnInfo> parseClass(Class clz) {
        List<ExcelColumnInfo> list = ReflectUtil.getAllDeclareFields(clz).stream()
                .filter(field -> field.isAnnotationPresent(ExcelColumn.class))
                .map(field -> {
                    field.setAccessible(true);
                    ExcelColumnInfo columnInfo = new ExcelColumnInfo();
                    columnInfo.setField(field);
                    columnInfo.setExcelColumn(field.getAnnotation(ExcelColumn.class));
                    return columnInfo;
                })
                .sorted(Comparator.comparingInt(a -> a.getExcelColumn().sort()))
                .collect(Collectors.toList());

        return list;
    }


}
