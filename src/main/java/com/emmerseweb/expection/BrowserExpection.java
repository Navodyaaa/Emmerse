package com.emmerseweb.expection;

public class BrowserExpection extends CustomExpection {
    public BrowserExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public BrowserExpection(String message) {
        super(message);
    }
}
