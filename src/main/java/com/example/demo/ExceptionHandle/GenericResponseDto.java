package com.example.demo.ExceptionHandle;

import org.springframework.stereotype.Component;


public class GenericResponseDto {

    String message;

    public GenericResponseDto() {
    }

    public GenericResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
