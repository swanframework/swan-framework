package com.swan.poi.config;

import com.swan.core.utils.ListUtil;
import com.swan.poi.handler.impl.*;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

/** 字段处理器
 * @author zongf
 * @since 2023-05-18
 **/
public class CellHandlerSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> list = new ArrayList<>();
        list.add(BigDecimalHandler.class.getName());
        list.add(DoubleCellHandler.class.getName());
        list.add(NumberCellHandler.class.getName());
        list.add(DateCellHandler.class.getName());
        list.add(DefaultCellHandler.class.getName());

        return ListUtil.toArray(list, String.class);
    }

}
