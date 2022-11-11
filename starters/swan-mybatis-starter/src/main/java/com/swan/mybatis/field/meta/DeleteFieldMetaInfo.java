package com.swan.mybatis.field.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class DeleteFieldMetaInfo extends FieldMetaInfo{

    /** 标识已删除的值 */
    private String yes;

    /** 表示未删除的值 */
    private String no;

}
