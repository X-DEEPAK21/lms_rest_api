package com.example.demo.ResponseDto;

import com.example.demo.Entity.User;

public class RegisterResponseDto {
    User user;
    String response;

    public RegisterResponseDto() {
    }

    public RegisterResponseDto(User user, String response) {
        this.user = user;
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
