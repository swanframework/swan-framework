package com.swan.web.vo;

import com.swan.web.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zongf
 * @date 2020-10-22
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;

    /** 请求成功
     * @return BaseResponse<T>
     * @author zongf
     * @date 2021-07-14
     */
    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    /** 请求成功
     * @param data 数据
     * @return BaseResponse<T>
     * @author zongf
     * @date 2021-07-14
     */
    public static <T> BaseResponse<T> success(T data) {
        return success(data, null);
    }

    /** 请求成功
     * @param data 数据
     * @param message 响应信息
     * @return BaseResponse<T>
     * @author zongf
     * @date 2021-07-14
     */
    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(ResponseCode.SUCCESS, message, data);
    }

    /** 请求失败
     * @param code 状态码
     * @param message 错误提示
     * @return BaseResponse<T>
     * @author zongf
     * @date 2021-07-14
     */
    public static <T> BaseResponse<T> fail(Integer code, String message) {
        return fail(null, code, message);
    }


    /** 请求失败
     * @param data 数据
     * @param code 状态码
     * @param message 错误提示
     * @return BaseResponse<T>
     * @author zongf
     * @date 2021-07-14
     */
    public static <T> BaseResponse<T> fail(T data, Integer code, String message) {
        return new BaseResponse<>(code, message, data);
    }


    /** 判断返回值是否成功
     * @return boolean
     * @author zongf
     * @date 2021-08-16
     */
    public boolean isSuccess() {
        return ResponseCode.SUCCESS == code;
    }

}
