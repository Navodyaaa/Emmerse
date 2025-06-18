package com.emmerseweb.expection;

public class GlobelExpectionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Uncaught exception in thread " + t.getName());
        System.out.println("Expection: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }

}
