package com.example.demo.ResponseDto;

import com.example.demo.Entity.Role;
import java.util.List;

public class ProfileResponseDto {
    String email;
    Role role;
    Boolean isVerified;
    List<String> EnrolledCourses;
    Integer EnrolledCount;

    public ProfileResponseDto(String email, Role role, Boolean isVerified) {
        this.email = email;
        this.role = role;
        this.isVerified = isVerified;

    }

    public String getEmail() {
        return email;
    }

    public List<String> getEnrolledCourses() {
        return EnrolledCourses;
    }

    public void setEnrolledCourses(List<String> enrolledCourses) {
        EnrolledCourses = enrolledCourses;
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

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Integer getEnrolledCount() {
        return EnrolledCount;
    }

    public void setEnrolledCount(Integer enrolledCount) {
        EnrolledCount = enrolledCount;
    }
}
