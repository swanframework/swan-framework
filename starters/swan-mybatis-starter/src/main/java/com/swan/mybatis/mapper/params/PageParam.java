package com.swan.mybatis.mapper.params;

import lombok.Getter;
import lombok.Setter;

/** 分页参数
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class PageParam {

    /** 页码 */
    private Integer page;

    /** 每页大小*/
    private Integer pageSize;

    private PageParam(Integer page, Integer pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }

    public static PageParam create(Integer page, Integer pageSize) {
        if (page < 1) {
            page = 1;
        }
        return new PageParam(page, pageSize);
    }

}
