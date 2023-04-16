package com.swan.minio.exception;


import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.exception.SwanBaseException;

/**
 * @author zongf
 * @since 2021-07-30
 */
public class SwanMinioException extends SwanBaseException {
    public SwanMinioException() {
        super();
    }

    public SwanMinioException(Throwable cause) {
        super(cause);
    }

    public SwanMinioException(Integer code, String message) {
        super(code, message);
    }

    public SwanMinioException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SwanMinioException(String message, Throwable cause) {
        super(ExceptionCodeEnum.MINIO.code(), message);
    }
}
