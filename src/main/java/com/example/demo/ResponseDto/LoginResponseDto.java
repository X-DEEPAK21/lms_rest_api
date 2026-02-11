package com.example.demo.ResponseDto;

import com.example.demo.Entity.Role;

public class LoginResponseDto {
    String jwt_token;
    String response;
    String type;
    String email;
    Role role;
    String time;

    public LoginResponseDto(String jwt_token, String response, String type, String email, Role role, String time) {
        this.jwt_token = jwt_token;
        this.response = response;
        this.type = type;
        this.email = email;
        this.role = role;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
