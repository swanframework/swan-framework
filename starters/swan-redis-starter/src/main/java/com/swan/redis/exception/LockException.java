package com.swan.redis.exception;

import com.swan.core.exception.SwanBaseException;

/**锁异常
 * @author zongf
 * @date 2021-05-15
 */
public class LockException extends SwanBaseException {
    public LockException() {
        super();
    }

    public LockException(Throwable cause) {
        super(cause);
    }

    public LockException(Integer code, String message) {
        super(code, message);
    }

    public LockException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
