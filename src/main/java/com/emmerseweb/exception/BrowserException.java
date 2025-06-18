package com.emmerseweb.exception;

public class BrowserException extends CustomException {

  public BrowserException(String message, Throwable cause) {
    super(message, cause);
  }

  public BrowserException(String message) {
    super(message);
  }
}
