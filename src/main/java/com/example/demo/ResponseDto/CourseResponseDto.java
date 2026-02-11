package com.example.demo.ResponseDto;

import com.example.demo.Repository.CourseRepo;

import java.time.LocalDateTime;

public class CourseResponseDto {
    String courseName;
    String response;
    Long courseId;
    LocalDateTime expireAt;
    String status;

    public CourseResponseDto() {
    }

    public CourseResponseDto(String courseName, String response,Long courseId, LocalDateTime expireAt,String status) {
        this.courseName = courseName;
        this.response = response;
        this.courseId=courseId;
        this.expireAt = expireAt;
        this.status=status;
    }

    public CourseResponseDto(String courseName, String response) {
        this.courseName = courseName;
        this.response = response;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}
