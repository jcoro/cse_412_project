package com.example.cse_412_project.exceptions;

/**
 * UserAlreadyExistsException.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/
public class UserAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
