 package com.swan.test.mybatis.codes;

import lombok.Getter;
import lombok.Setter;
import com.swan.mybatis.anno.Id;

import java.util.Date;

 /** 基础信息-地址
 * @author zongf
 * @date 2021-03-01
 */
 @Getter @Setter
 public class AbsEntity {

     /** 主键id */
     @Id
     private Integer id;

     /** 创建时间 */
     private Date createTime;

     /** 更新时间 */
     private Date updateTime;

     /** 版本号 */
     private Integer version;

     /** 是否删除 */
     private String del;

 }
