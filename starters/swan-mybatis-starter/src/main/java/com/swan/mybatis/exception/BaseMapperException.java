package com.swan.mybatis.exception;

import com.swan.core.exception.SwanBaseException;

/** BaseMapperException 异常
 * @author zongf
 * @date 2021-01-11
 */
public class BaseMapperException extends SwanBaseException {

    public BaseMapperException() {
        super();
    }

    public BaseMapperException(String message) {
        super(message);
    }

    public BaseMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseMapperException(Throwable cause) {
        super(cause);
    }

    protected BaseMapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
