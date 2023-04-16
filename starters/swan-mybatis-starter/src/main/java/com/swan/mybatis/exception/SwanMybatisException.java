package com.swan.mybatis.exception;

import com.swan.core.exception.SwanBaseException;

/** BaseMapperException 异常
 * @author zongf
 * @since 2021-01-11
 */
public class SwanMybatisException extends SwanBaseException {

    public SwanMybatisException() {
        super();
    }

    public SwanMybatisException(Throwable cause) {
        super(cause);
    }

    public SwanMybatisException(Integer code, String message) {
        super(code, message);
    }

    public SwanMybatisException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
