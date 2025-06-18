package com.emmerseweb.expection;

public class CustomExpection extends RuntimeException {
    private String message;
    private Throwable cause;

    public CustomExpection(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public CustomExpection(String message) {
        super(message);
        this.message = message;
    }
}
