package com.example.demo.Exception;

public class CourseNotExist extends RuntimeException{
    public CourseNotExist(String msg){
        super(msg);
    }
}
