package com.swan.generator.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** java 属性
 * @author zongf
 * @date 2019-11-30
 */
@Setter @Getter @ToString
public class EntityFieldMeta {

    // 名称
    private String name;

    // 类型
    private String type;

    // 注释
    private String comment;

    // 导入依赖
    private String importType;
    
    // 列名
    private String columnName;

	// jdbc类型
	private String jdbcType;

    // 是否是主键列
    private boolean pkColumn;

	public EntityFieldMeta() {
        super();
    }

}
