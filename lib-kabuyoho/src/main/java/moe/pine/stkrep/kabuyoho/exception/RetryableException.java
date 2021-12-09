package moe.pine.stkrep.kabuyoho.exception;

import java.io.Serial;

public class RetryableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2173095340168200544L;

    public RetryableException(String message) {
        super(message);
    }

    public RetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetryableException(Throwable cause) {
        super(cause);
    }
}
