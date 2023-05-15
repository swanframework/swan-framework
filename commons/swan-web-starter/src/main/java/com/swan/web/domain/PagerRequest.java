package com.swan.web.domain;

import lombok.Getter;
import lombok.Setter;

/** 分页查询
 * @author zongf
 * @since 2021-11-03
 */
@Setter @Getter
public class PagerRequest<T> {

    private Integer pageNum;

    private Integer pageSize;

    private T lastId;

}
