package com.example.demo.Repository;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EnrollmentRepo extends JpaRepository<Enrollment,Long> {

    Boolean existsByUserIdAndCourseId(User user, Course course);

    Long countByExpiresAtBefore(LocalDateTime localDateTime);
    Long countByExpiresAtAfter(LocalDateTime localDateTime);
    Long countBypurchasedAtAfter(LocalDateTime localDateTime);

    @Override
    Page<Enrollment> findAll(Pageable pageable);
}
