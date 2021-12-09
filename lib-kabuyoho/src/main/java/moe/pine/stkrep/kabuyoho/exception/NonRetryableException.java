package moe.pine.stkrep.kabuyoho.exception;

import java.io.Serial;

public class NonRetryableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2667587766919314417L;

    public NonRetryableException(String message) {
        super(message);
    }

    public NonRetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonRetryableException(Throwable cause) {
        super(cause);
    }
}
