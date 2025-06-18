package com.emmerseweb.exception;;

public class NoSuchElementException extends CustomException {

  public NoSuchElementException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoSuchElementException(String message) {
    super(message);
  }
}
