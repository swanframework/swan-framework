package com.swan.web.domain;

import com.swan.web.constant.ResponseCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** 分页响应
 * @author zongf
 * @since 2021-11-03
 */
@Data
public class PagerResponse<T> extends AbsResponse {

    private Integer pages;

    private Integer total;

    private List<T> data;

    public PagerResponse() {
    }

    public PagerResponse(List<T> data) {
        this.data = data;
    }

    public PagerResponse(List<T> data, String message) {
        super(ResponseCode.SUCCESS, message);
        this.data = data;
    }

    public PagerResponse(List<T> data, Integer code, String message) {
        super(code, message);
        this.data = data;
    }

    /** 请求成功
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> PagerResponse<T> success() {
        return success(null);
    }

    /** 请求成功
     * @param data 数据
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> PagerResponse<T> success(List<T> data) {
        return success(data, null);
    }

    /** 请求成功
     * @param data 数据
     * @param message 响应信息
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> PagerResponse<T> success(List<T> data, String message) {
        return new PagerResponse<>(data, ResponseCode.SUCCESS, message);
    }

    /** 请求失败
     * @param code 状态码
     * @param message 错误提示
     * @return BaseResponse<T>
     * @author zongf
     * @since 2021-07-14
     */
    public static <T> PagerResponse<T> fail(Integer code, String message) {
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
    public static <T> PagerResponse<T> fail(List<T> data, Integer code, String message) {
        return new PagerResponse<>(data, code, message);
    }

}
