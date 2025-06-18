package com.emmerseweb.exception;

public class DataReadException extends CustomException {

  public DataReadException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataReadException(String message) {
    super(message);
  }
}
