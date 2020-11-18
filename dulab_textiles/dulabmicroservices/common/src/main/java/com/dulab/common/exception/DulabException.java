package com.dulab.common.exception;

public class DulabException extends RuntimeException {

    /**
     * Constructor method.
     */
    public DulabException() {
        super();
    }

    /**
     * Constructor method.
     * @param message - error message
     */
    public DulabException(final String message) {
        super(message);
    }

    /**
     * Constructor method.
     * @param throwable - exception object
     */
    public DulabException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor method.
     * @param message   - error message
     * @param throwable - exception object
     */
    public DulabException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
