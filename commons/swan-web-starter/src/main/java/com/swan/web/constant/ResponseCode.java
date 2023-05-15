package com.swan.web.constant;

/** 响应状态码
 *  1) 0: 成功
 *  1) 1-999: 系统异常
 *  2) 100000-999999: 业务异常
 *  3) 999999: 未知异常
 * @author zongf
 * @since 2021-08-16
 */
public class ResponseCode {

    /** 成功 */
    public static final Integer SUCCESS = 0;

    /** 未知异常 */
    public static final Integer UNKNOWN = -1;

    /** 参数非法 */
    public static final Integer PARAM_ERROR = 101;

    /** 参数非法 */
    public static final Integer PATH_NOT_FOUND_ERROR = 400;



}
