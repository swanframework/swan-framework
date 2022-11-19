package com.swan.generator.persistence.po;

import lombok.Getter;
import lombok.Setter;

/** 数据库Schema信息
 * @author zongf
 * @date 2019-11-30
 */
@Setter @Getter
public class SchemaPO {

    private String catalogName;
    
    private String schemaName;
    
    private String defaultCharacterName;
    
    private String defaultCollationName;
    
    private String sqlPath;

}
