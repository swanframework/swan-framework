package com.swan.web.domain;

import com.swan.web.constant.ResponseCode;
import lombok.*;

/**
 * @author zongf
 * @since 2020-10-22
 */
@Data
public class BaseResponse<T> extends AbsResponse {

    // 数据
    private T data;

    public BaseResponse() {
    }
    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(T data, String message) {
        super(ResponseCode.SUCCESS, message);
        this.data = data;
    }

    public BaseResponse(T data, Integer code, String message) {
        super(code, message);
        this.data = data;
    }

    /** 请求成功
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    /** 请求成功
     * @param data 数据
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> BaseResponse<T> success(T data) {
        return success(data, null);
    }

    /** 请求成功
     * @param data 数据
     * @param message 响应信息
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(data, ResponseCode.SUCCESS, message);
    }

    /** 请求失败
     * @param code 状态码
     * @param message 错误提示
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
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
     * @since 2021-07-14
     */
    public static <T> BaseResponse<T> fail(T data, Integer code, String message) {
        return new BaseResponse<>(data, code, message);
    }

}
