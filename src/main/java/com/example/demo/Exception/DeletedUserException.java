package com.example.demo.Exception;

public class DeletedUserException extends RuntimeException{
    public DeletedUserException(String msg){
        super(msg);
    }

}
