package com.swan.web.domain;

import com.swan.web.constant.ResponseCode;
import lombok.*;

/**
 * @author zongf
 * @since 2020-10-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbsResponse {

    /** 响应码 */
    private Integer code;

    /** 错误描述  */
    private String message;

    /** 判断返回值是否成功
     * @return boolean
     * @author zongf
     * @since 2021-08-16
     */
    public boolean isSuccess() {
        return ResponseCode.SUCCESS == code;
    }

}
