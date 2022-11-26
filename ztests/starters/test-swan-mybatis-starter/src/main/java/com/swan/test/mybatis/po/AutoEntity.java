package com.swan.test.mybatis.po;

import com.swan.mybatis.anno.AutoTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.swan.mybatis.anno.Id;
import com.swan.mybatis.anno.Table;
import com.swan.mybatis.enums.IdGeneratorType;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zongf
 * @since 2021-01-07
 */
@Table(name = "t_auto")
@Setter @Getter @ToString
@FieldNameConstants
public class AutoEntity implements Serializable {

    @Id(generatorType = IdGeneratorType.AUTO_INC)
    protected Long id;

    protected String name;

    protected Integer age;

    protected Date createTime;

    protected Date updateTime;

    public AutoEntity() {
    }

    public AutoEntity(String name) {
        this.name = name;
    }

    public AutoEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.createTime = updateTime = new Date();
    }

}

