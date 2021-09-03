package com.example.cse_412_project.exceptions;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * MethodNotSupportedException.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/
public class MethodNotSupportedException extends Exception {

    private RequestMethod method;

    public MethodNotSupportedException(RequestMethod method) {
        this.method = method;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }


}
