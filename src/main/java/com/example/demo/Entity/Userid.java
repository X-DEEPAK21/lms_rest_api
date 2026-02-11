package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_id")
public class Userid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
     Long id;
    @Column(nullable = false,unique = true)
     String userid;
    @Enumerated(EnumType.STRING)
     Role role;

    public Userid() {
    }

    public Userid( String userid, Role role) {
        this.userid = userid;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
