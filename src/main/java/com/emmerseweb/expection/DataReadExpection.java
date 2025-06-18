package com.emmerseweb.expection;

public class DataReadExpection extends CustomExpection {
    public DataReadExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public DataReadExpection(String message) {
        super(message);
    }
}
