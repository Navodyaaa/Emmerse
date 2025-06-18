package com.emmerseweb.expection;

public class NoSuchElementExpection extends CustomExpection {
    public NoSuchElementExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElementExpection(String message) {
        super(message);
    }
}
