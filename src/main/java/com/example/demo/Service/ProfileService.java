package com.example.demo.Service;

import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.User;
import com.example.demo.ResponseDto.ProfileResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Transactional
    public ProfileResponseDto getProfile(User user){
      List<String> courses=new ArrayList<>();
      for (Enrollment enrollment:user.getEnrollmentList()){
          courses.add(enrollment.getCourseId().getName());
      }
      ProfileResponseDto responseDto=new ProfileResponseDto(user.getEmail(),user.getRole(),true);
      responseDto.setEnrolledCourses(courses);
      responseDto.setEnrolledCount(courses.size());
      return responseDto;


    }
}
