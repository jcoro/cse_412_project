package com.example.cse_412_project.exceptions;

/**
 * UserDoesNotExistException.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/
public class UserDoesNotExistException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
        super();
    }

    public UserDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }

    public UserDoesNotExistException(Throwable cause) {
        super(cause);
    }

}
