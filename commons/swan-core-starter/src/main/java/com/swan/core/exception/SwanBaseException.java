package com.swan.core.exception;

/** 框架异常不需要定义 code
 * @author zongf
 * @since 2022-11-10
 **/
public class SwanBaseException extends RuntimeException{

    private Integer code;

    public SwanBaseException() {
        super();
    }

    public SwanBaseException(Throwable cause) {
        super(cause);
    }

    public SwanBaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SwanBaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }



}
