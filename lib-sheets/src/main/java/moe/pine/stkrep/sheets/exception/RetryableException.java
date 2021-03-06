package moe.pine.stkrep.sheets.exception;

import java.io.Serial;

public class RetryableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8222663365850683101L;

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
