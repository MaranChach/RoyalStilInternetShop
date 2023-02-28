package com.trantin.simpleweb.http.exceptions;

public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message){
        super(message);
    }
}
