 package com.swan.test.mybatis.codes;

import lombok.Getter;
import lombok.Setter;
import com.swan.mybatis.anno.Table;

 /** 基础信息-地址
* @author zongf
* @date 2021-03-03
*/
@Getter @Setter
@Table(name = "base_address")
public class JdAddressEntity extends AbsEntity{

    /** 名称 */
    private String name;

    /** 级别 */
    private Integer level;

    /** 父地址id */
    private Integer parentId;

    /** 外部id */
    private Integer exId;

}
