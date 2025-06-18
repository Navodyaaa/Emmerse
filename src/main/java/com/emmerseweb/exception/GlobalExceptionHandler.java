package com.emmerseweb.exception;


public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    
        public void uncaughtException(Thread t, Throwable e) {
          System.out.println("Uncaught Exception in Thread: " + t.getName());
          System.out.println("Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
      }