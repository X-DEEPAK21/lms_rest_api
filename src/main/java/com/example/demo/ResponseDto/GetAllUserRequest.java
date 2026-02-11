package com.example.demo.ResponseDto;

import com.example.demo.Entity.Role;

public class GetAllUserRequest {
    Long id;
    String email;
    String  role;

    public GetAllUserRequest() {
    }

    public GetAllUserRequest(Long id, String role, String email) {
        this.id = id;
        this.role = role;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String  getRole() {
        return role;
    }

    public void setRole(String  role) {
        this.role = role;
    }
}
