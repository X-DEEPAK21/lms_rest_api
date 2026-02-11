package com.example.demo.ResponseDto;

public class EmailUpdateResponseDto {
    String email;
    String response;

    public EmailUpdateResponseDto() {
    }

    public EmailUpdateResponseDto(String email, String response) {
        this.email = email;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
