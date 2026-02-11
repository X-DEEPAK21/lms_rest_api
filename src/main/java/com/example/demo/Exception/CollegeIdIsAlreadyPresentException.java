package com.example.demo.Exception;

public class CollegeIdIsAlreadyPresentException extends RuntimeException{
    public CollegeIdIsAlreadyPresentException(String msg){
        super(msg);
    }
}
