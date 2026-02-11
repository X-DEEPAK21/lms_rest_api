package com.example.demo.DataPanel;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Userid;
import com.example.demo.Repository.CourseRepo;
import com.example.demo.Repository.UserIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserIdRepo userid;
    @Autowired
    CourseRepo courseRepo;

    @Override
    public void run(String... args) throws Exception {
        if (userid.count() == 0) {
            userid.save(new Userid("STU-26-101", Role.ROLE_STUDENT));
            userid.save(new Userid("STU-26-102", Role.ROLE_STUDENT));
            userid.save(new Userid("TEACH-26-11", Role.ROLE_ADMIN));
        }
        if (courseRepo.count() == 0) {
            courseRepo.save(new Course("java-full stack", 15000));
            courseRepo.save(new Course("python-full stack", 12000));
            courseRepo.save(new Course("Mern stack", 7000));
            courseRepo.save(new Course("Oracle", 4000));

        }
    }
}
