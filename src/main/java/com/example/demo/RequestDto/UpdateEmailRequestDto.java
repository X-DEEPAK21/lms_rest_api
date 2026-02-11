package com.example.demo.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateEmailRequestDto {

    @NotBlank(message = "Email cannot be blank")
    @Email
    String email;
    String response;

    public UpdateEmailRequestDto() {
    }

    public UpdateEmailRequestDto(String email, String response) {
        this.email = email;
        this.response = response;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
