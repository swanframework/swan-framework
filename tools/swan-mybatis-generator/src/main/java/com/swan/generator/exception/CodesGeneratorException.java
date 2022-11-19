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

    public CodesGeneratorException(String message) {
        super(message);
    }

    public CodesGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }


}
