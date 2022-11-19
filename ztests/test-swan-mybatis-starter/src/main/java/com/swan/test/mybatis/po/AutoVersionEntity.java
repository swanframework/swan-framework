package com.swan.test.mybatis.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.swan.mybatis.anno.Id;
import com.swan.mybatis.anno.Table;
import com.swan.mybatis.anno.Version;
import com.swan.mybatis.enums.IdGeneratorType;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @author zongf
 * @since 2021-01-07
 */
@Table(name = "t_auto_version")
@Setter @Getter @ToString
@FieldNameConstants
public class AutoVersionEntity implements Serializable {

    @Id(generatorType = IdGeneratorType.AUTO_INC)
    private Long id;

    private String name;

    private Integer age;

    @Version(start = 10)
    private Integer version;

    public AutoVersionEntity() {
    }

    public AutoVersionEntity(String name) {
        this.name = name;
    }


    public AutoVersionEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}

