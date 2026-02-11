package com.example.demo.Exception;

public class UserExistException extends RuntimeException{
    public UserExistException(String s ){
        super(s);
    }
}
