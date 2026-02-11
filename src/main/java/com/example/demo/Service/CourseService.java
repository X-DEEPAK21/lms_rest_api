package com.example.demo.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.User;
import com.example.demo.Exception.CourseNotExist;
import com.example.demo.Exception.UserAlreadyEnrollException;
import com.example.demo.Repository.CourseRepo;
import com.example.demo.Repository.EnrollmentRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.ResponseDto.CourseDetailsResponseDto;
import com.example.demo.ResponseDto.CourseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    EnrollmentRepo enrollmentRepo;
    @Autowired
    UserRepo userRepo;

    @Transactional
   public Enrollment enrollByCourseId(User user, Long courseId){

       Optional<Course> optional= courseRepo.findById(courseId);
       if(optional.isEmpty())throw new CourseNotExist("Course not found ");
       Course course=optional.get();

        if (enrollmentRepo.existsByUserIdAndCourseId(user,course)) {
            throw new UserAlreadyEnrollException("User already enrolled in this course");
        }
        LocalDateTime purchaseAt=LocalDateTime.now();
        LocalDateTime expireAt=purchaseAt.plusMonths(12);

        Enrollment enrollment=new Enrollment(user,course,purchaseAt,expireAt);
        Enrollment enrollment1=enrollmentRepo.save(enrollment);
        return enrollment1;

    }
    public String getStatus(LocalDateTime expiresAt) {
        return expiresAt.isAfter(LocalDateTime.now())
                ? "ACTIVE"
                : "EXPIRED";
    }
    public List<Enrollment> getEnrollments(Long userid){

        User user=userRepo.findById(userid).get();
        List<Enrollment> enrollmentList =user.getEnrollmentList();
       /* List<Course> courseList=enrollmentList.stream().map(enrollment -> {
           return enrollment.getCourseId();
        }).toList();
        return  courseList;*/
        return  enrollmentList;
    }
    public Long daysLeft(LocalDateTime expire_at){
   LocalDateTime now=LocalDateTime.now();
      if(expire_at.isBefore(now))return 0L;
      return ChronoUnit.DAYS.between(now,expire_at);
    }


    public List<CourseDetailsResponseDto> getCourseResponse(List<Enrollment> enrollments){
       List<CourseDetailsResponseDto> courseDetailsResponseDtos=new ArrayList<>();
        for(Enrollment enrollment:enrollments){
            CourseDetailsResponseDto courseResponseDto= new CourseDetailsResponseDto();
            courseResponseDto.setCourse_id(enrollment.getCourseId().getId());
            courseResponseDto.setStatus(this.getStatus(enrollment.getExpiresAt()));
            courseResponseDto.setDaysLeft(this.daysLeft(enrollment.getExpiresAt())+"Days Left");
            courseDetailsResponseDtos.add(courseResponseDto);
        }
        return courseDetailsResponseDtos;


    }

}
