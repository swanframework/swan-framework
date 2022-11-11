package com.swan.test.mybatis.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.swan.mybatis.anno.Id;
import com.swan.mybatis.anno.Table;
import com.swan.mybatis.enums.IdGeneratorType;

import java.io.Serializable;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Table(name = "t_auto")
@Setter @Getter @ToString
public class AutoEntity implements Serializable {

    @Id(generatorType = IdGeneratorType.AUTO_INC)
    protected Long id;

    protected String name;

    protected Integer age;

    public AutoEntity() {
    }

    public AutoEntity(String name) {
        this.name = name;
    }

    public AutoEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}

