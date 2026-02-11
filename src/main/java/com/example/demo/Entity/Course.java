package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

import java.time.LocalDateTime;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    Integer price;

    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Enrollment>enrollmentList;

    public Course() {
    }

    public Course( String name, Integer price) {

        this.name = name;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
