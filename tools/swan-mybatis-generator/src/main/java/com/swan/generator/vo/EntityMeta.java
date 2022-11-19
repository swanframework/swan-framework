package com.swan.generator.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/** 生成一个java类需要的元信息
 * @author zongf
 * @date 2019-11-30
 */
@Setter @Getter @ToString
public class EntityMeta{

    // 表名称
    private String tableName;

    /** 包名 */
    private String packageName;

    /** 需要导入的类 */
    private List<String> importList = new ArrayList<>();

    /** 注释 */
    private String classComment;

    /** 类名 */
    private String className;

    // 属性列表
    private List<EntityFieldMeta> fields = new ArrayList<>();

}
