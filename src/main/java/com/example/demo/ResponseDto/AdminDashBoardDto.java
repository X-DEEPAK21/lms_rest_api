package com.example.demo.ResponseDto;

public class AdminDashBoardDto {
    Long totalUser;
    Long totalCourse;
    Long totalEnrollment;
    Long expireEnrollment;
    Long activeEnrollment;
    Long recentEnrollment;

    public AdminDashBoardDto() {
    }

    public AdminDashBoardDto(Long totalUser, Long totalCourse, Long totalEnrollment, Long expireEnrollment, Long activeEnrollment, Long recentEnrollment) {
        this.totalUser = totalUser;
        this.totalCourse = totalCourse;
        this.totalEnrollment = totalEnrollment;
        this.expireEnrollment = expireEnrollment;
        this.activeEnrollment = activeEnrollment;
        this.recentEnrollment = recentEnrollment;
    }

    public Long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Long totalUser) {
        this.totalUser = totalUser;
    }

    public Long getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(Long totalCourse) {
        this.totalCourse = totalCourse;
    }

    public Long getTotalEnrollment() {
        return totalEnrollment;
    }

    public void setTotalEnrollment(Long totalEnrollment) {
        this.totalEnrollment = totalEnrollment;
    }

    public Long getExpireEnrollment() {
        return expireEnrollment;
    }

    public void setExpireEnrollment(Long expireEnrollment) {
        this.expireEnrollment = expireEnrollment;
    }

    public Long getActiveEnrollment() {
        return activeEnrollment;
    }

    public void setActiveEnrollment(Long activeEnrollment) {
        this.activeEnrollment = activeEnrollment;
    }

    public Long getRecentEnrollment() {
        return recentEnrollment;
    }

    public void setRecentEnrollment(Long recentEnrollment) {
        this.recentEnrollment = recentEnrollment;
    }
}
