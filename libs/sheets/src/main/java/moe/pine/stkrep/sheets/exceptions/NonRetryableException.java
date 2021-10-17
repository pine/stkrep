package moe.pine.stkrep.sheets.exceptions;

import java.io.Serial;

public class NonRetryableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7133840056787717490L;

    public NonRetryableException(String message, Throwable cause) {
        super(message, cause);
    }
}
