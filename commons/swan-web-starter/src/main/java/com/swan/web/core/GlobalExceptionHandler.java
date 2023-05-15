package com.swan.web.core;

import com.swan.core.exception.SwanBaseException;
import com.swan.web.config.SwanWebProperties;
import com.swan.web.constant.ResponseCode;
import com.swan.web.domain.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** 默认全局异常处理
 * @author zongf
 * @since 2021-08-16
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Integer RESPONSE_ERROR_CODE = 5100;

    @Autowired
    private SwanWebProperties swanWebProperties;

    // 处理参数绑定异常, @Valid
    @ExceptionHandler(BindException.class)
    public BaseResponse validateException(HttpServletResponse response, BindException ex) {

        // 处理响应流: 修改状态码
        handleResponse(response);
        log.warn("参数绑定异常:", ex);

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage()).append(";");
        }

        return BaseResponse.fail(ResponseCode.PARAM_ERROR, sb.toString());
    }

    // 处理嵌套参数异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {

        // 处理响应流: 修改状态码
        handleResponse(response);
        log.warn("参数绑定异常:", ex);

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage()).append(";");
        }

        return BaseResponse.fail(ResponseCode.PARAM_ERROR, sb.toString());
    }

    // sboot 框架内部异常处理
    @ExceptionHandler(SwanBaseException.class)
    public BaseResponse sbootException(HttpServletResponse response, SwanBaseException ex) {
        log.warn("系统内部异常:", ex);

        // 处理响应流: 修改状态码
        handleResponse(response);

        return BaseResponse.fail(ResponseCode.UNKNOWN, ex.getMessage());
    }

    // 处理:未知系统异常
    @ExceptionHandler(Exception.class)
    public BaseResponse unknownException(HttpServletResponse response, Exception ex) {
        log.error("未知系统异常:", ex);

        // 处理响应流: 修改状态码
        handleResponse(response);

        return BaseResponse.fail(ResponseCode.UNKNOWN, swanWebProperties.getException().getUnknownMessage());
    }

    // 处理: 404 请求地址不存在
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse notFundException(HttpServletResponse response, Exception ex) {
        log.warn("请求地址不存在:", ex);

        // 处理响应流: 修改状态码
        handleResponse(response);

        return BaseResponse.fail(ResponseCode.PATH_NOT_FOUND_ERROR, "请求地址不存在");
    }

    // 处理响应
    private void handleResponse(HttpServletResponse response) {
        // 设置响应状态码为自定义状态码
        if (!swanWebProperties.getException().getResponseOk()) {
            response.setStatus(RESPONSE_ERROR_CODE);
        }
        // 设置响应类型为 json
        response.setContentType("application/json; charset=utf-8");
    }
}
