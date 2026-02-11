package com.example.demo.Exception;

public class InvalidCollegeIdException extends  RuntimeException{
    public InvalidCollegeIdException(String msg){
        super(msg);
    }
}
