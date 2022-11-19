package com.swan.mybatis.exception;

import com.swan.core.exception.SwanBaseException;

/** BaseMapperException 异常
 * @author zongf
 * @date 2021-01-11
 */
public class SwanMybatisException extends SwanBaseException {

    public SwanMybatisException() {
        super();
    }

    public SwanMybatisException(String message) {
        super(message);
    }

    public SwanMybatisException(String message, Throwable cause) {
        super(message, cause);
    }

    public SwanMybatisException(Throwable cause) {
        super(cause);
    }

    protected SwanMybatisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
