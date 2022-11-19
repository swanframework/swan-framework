package com.swan.mybatis.mapper.field.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class FieldMetaInfo {

    /** 字段名称 */
    private String columnName;

    /** java 属性名称 */
    private String propertyName;

    /** 类型全类名 **/
    private String type;

}
