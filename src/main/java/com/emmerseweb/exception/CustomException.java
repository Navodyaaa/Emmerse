package com.emmerseweb.exception;;

public class CustomException extends RuntimeException {
  private String message;
  private Throwable cause;

  public CustomException(String message, Throwable cause) {
    super(message, cause);
    this.message = message;
    this.cause = cause;
  }

  public CustomException(String message) {
    super(message);
    this.message = message;
  }
}
