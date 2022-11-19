package com.swan.test.mybatis.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.swan.mybatis.anno.Delete;
import com.swan.mybatis.anno.Id;
import com.swan.mybatis.anno.Table;
import com.swan.mybatis.enums.IdGeneratorType;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Table(name = "t_auto_del")
@Setter @Getter @ToString
@FieldNameConstants
public class AutoDelEntity implements Serializable {

    @Id(generatorType = IdGeneratorType.AUTO_INC)
    private Long id;

    private String name;

    private Integer age;

    @Delete(yes = "-1", no = "0")
    private Integer del;

    public AutoDelEntity() {
    }

    public AutoDelEntity(String name) {
        this.name = name;
    }

    public AutoDelEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}

