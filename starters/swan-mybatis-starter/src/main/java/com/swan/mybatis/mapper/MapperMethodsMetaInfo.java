package com.swan.mybatis.mapper;

import com.swan.core.utils.InterfaceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** 方法标识，判断需要生成那些方法
 * @author zongf
 * @since 2021-02-26
 */
@Setter @Getter @ToString
public class MapperMethodsMetaInfo {

    // 方法名
    private List<String> methodNames;

    public MapperMethodsMetaInfo(Class type) {
        Set<Class> interfaces = InterfaceUtil.getInterfaces(type);
        this.methodNames = interfaces.stream()
                .map(clz -> clz.getSimpleName())
                .map(name -> {
                    int idx = name.indexOf("Method");
                    if (idx != -1) {
                        return name.substring(0, 1).toLowerCase() + name.substring(1, idx);
                    }else {
                        return name;
                    }
                })
                .collect(Collectors.toList());
    }
}
