package com.example.demo.ResponseDto;

import java.util.List;

public class CourseDetailsResponseDto {
    Long course_id;
    String response;
    String daysLeft;
    String  status;

    public CourseDetailsResponseDto() {
    }

    public CourseDetailsResponseDto(Long course_id, String response, String daysLeft, String status) {
        this.course_id = course_id;
        this.response = response;
        this.daysLeft = daysLeft;
        this.status = status;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        this.daysLeft = daysLeft;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
