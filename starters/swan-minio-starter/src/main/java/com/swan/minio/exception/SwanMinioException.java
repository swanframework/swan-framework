package com.swan.minio.exception;


import com.swan.core.exception.SwanBaseException;

/**
 * @author zongf
 * @date 2021-07-30
 */
public class SwanMinioException extends SwanBaseException {
    public SwanMinioException() {
    }

    public SwanMinioException(String message) {
        super(message);
    }

    public SwanMinioException(String message, Throwable cause) {
        super(message, cause);
    }


}
