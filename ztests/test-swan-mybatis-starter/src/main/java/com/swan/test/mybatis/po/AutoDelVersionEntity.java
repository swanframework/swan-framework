package com.swan.test.mybatis.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.swan.mybatis.anno.*;
import com.swan.mybatis.enums.IdGeneratorType;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @author zongf
 * @date 2021-01-07
 */
@Table(name = "t_auto_del_version")
@Setter @Getter @ToString
@FieldNameConstants
public class AutoDelVersionEntity implements Serializable {

    @Id(generatorType = IdGeneratorType.AUTO_INC)
    private Long id;

    private String name;

    private Integer age;

    @Ignore
    private String remark;

    @Version(start = 10)
    private Integer version;

    @Delete(yes = "-1", no = "0")
    private Integer del;

    public AutoDelVersionEntity() {
    }

    public AutoDelVersionEntity(String name) {
        this.name = name;
    }

    public AutoDelVersionEntity(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public AutoDelVersionEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public AutoDelVersionEntity(String name, Integer age, String remark) {
        this.name = name;
        this.remark = remark;
        this.age = age;
    }

}

