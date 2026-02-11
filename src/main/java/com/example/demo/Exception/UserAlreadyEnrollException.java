package com.example.demo.Exception;

public class UserAlreadyEnrollException extends RuntimeException{

    public UserAlreadyEnrollException(String msg){
        super(msg);
    }
}
