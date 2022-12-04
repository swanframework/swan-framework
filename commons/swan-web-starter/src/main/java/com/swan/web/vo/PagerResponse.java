package com.swan.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** 分页响应
 * @author zongf
 * @since 2021-11-03
 */
@Setter @Getter
public class PagerResponse<T> {

    private Integer pages;

    private Long total;

    private List<T> data;

}
