package com.swan.generator.exception;


import com.swan.core.exception.SwanBaseException;

/**
 * @author zongf
 * @since 2021-11-02
 */
public class CodesGeneratorException extends SwanBaseException {
    public CodesGeneratorException() {
        super();
    }

    public CodesGeneratorException(Throwable cause) {
        super(cause);
    }

    public CodesGeneratorException(Integer code, String message) {
        super(code, message);
    }

    public CodesGeneratorException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
