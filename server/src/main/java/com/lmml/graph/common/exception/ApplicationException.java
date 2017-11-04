package com.lmml.graph.common.exception;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -1963689178920485227L;

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}