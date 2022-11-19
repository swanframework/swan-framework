package com.swan.redis.exception;

import com.swan.core.exception.SwanBaseException;

/**锁异常
 * @author zongf
 * @date 2021-05-15
 */
public class LockException extends SwanBaseException {

    public LockException() {
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

}
