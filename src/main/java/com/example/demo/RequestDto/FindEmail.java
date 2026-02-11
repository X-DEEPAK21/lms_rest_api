package com.example.demo.RequestDto;

public class FindEmail {
    Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    String email;

    public FindEmail() {
    }

    public FindEmail(Long userid, String email) {
        this.userid = userid;
        this.email = email;
    }

    public FindEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
