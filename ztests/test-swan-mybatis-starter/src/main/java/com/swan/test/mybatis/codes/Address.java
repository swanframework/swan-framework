 package com.swan.test.mybatis.codes;

import lombok.Getter;
import lombok.Setter;

/** 基础信息-地址
* @author zongf
* @date 2021-03-01
*/
@Getter @Setter
public class Address extends AbsEntity{

    /**  */
    private String name;

    /** 级别 */
    private Integer level;

    /** 父地址id */
    private Integer parentId;

}
