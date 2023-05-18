package com.swan.poi.exception;

import com.swan.core.exception.SwanBaseException;

/**
 * @author zongf
 * @since 2023-05-18
 **/
public class SwanPoiException extends SwanBaseException {

    public SwanPoiException() {
    }

    public SwanPoiException(Throwable cause) {
        super(cause);
    }

    public SwanPoiException(Integer code, String message) {
        super(code, message);
    }

    public SwanPoiException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
