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

    public SwanValidateException(String message) {
        super(message);
    }

    public SwanValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SwanValidateException(Throwable cause) {
        super(cause);
    }

    protected SwanValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
