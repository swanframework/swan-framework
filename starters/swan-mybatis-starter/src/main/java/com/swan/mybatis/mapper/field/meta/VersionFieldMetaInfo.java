package com.swan.mybatis.mapper.field.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class VersionFieldMetaInfo extends FieldMetaInfo{

    /** 版本号开始 */
    private Integer versionStart = 0;

}
