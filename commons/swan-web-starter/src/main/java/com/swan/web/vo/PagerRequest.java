package com.swan.web.vo;

import lombok.Getter;
import lombok.Setter;

/** 分页查询
 * @author zongf
 * @date 2021-11-03
 */
@Setter @Getter
public class PagerRequest<T> {

    private Integer pageIndex;

    private Integer pageSize;

    private Long lastId;

    private T body;

}
