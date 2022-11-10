package com.swan.core.exception;

/** 框架异常不需要定义 code
 * @author zongf
 * @since 2022-11-10
 **/
public class SwanBaseException extends RuntimeException{

    public SwanBaseException() {
        super();
    }

    public SwanBaseException(String message) {
        super(message);
    }

    public SwanBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SwanBaseException(Throwable cause) {
        super(cause);
    }

    protected SwanBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
