package com.swan.web.excetion;

import com.swan.core.exception.SwanBaseException;

/**
 * @author zongf
 * @since 2022-11-20
 **/
public class SwanValidateException extends SwanBaseException {
    public SwanValidateException() {
        super();
    }

    public SwanValidateException(Throwable cause) {
        super(cause);
    }

    public SwanValidateException(Integer code, String message) {
        super(code, message);
    }

    public SwanValidateException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
