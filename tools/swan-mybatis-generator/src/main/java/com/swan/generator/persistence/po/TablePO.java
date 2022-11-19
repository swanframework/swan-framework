package com.swan.generator.persistence.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/** 数据库表信息
 * @author zongf
 * @since 2019-11-30
 */
@Setter @Getter
public class TablePO {

    // 表名
    private String tableName;

    // 注释
    private String comment;

    // 存储引擎
    private String engine;

    // 创建时间
    private Date createTime;

}
