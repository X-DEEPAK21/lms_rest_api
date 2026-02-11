package com.example.demo.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.User;
import com.example.demo.Exception.UserExistException;
import com.example.demo.Repository.CourseRepo;
import com.example.demo.Repository.EnrollmentRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.ResponseDto.AdminDashBoardDto;
import com.example.demo.ResponseDto.GetAllUserRequest;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    EnrollmentRepo enrollmentRepo;
    @Autowired
    UserRepo userRepo;

    public Long getAllUserCount(){
       return userRepo.count();
    }
    public Long getAllCourseCount(){
        return courseRepo.count();
    }
    public Long getAllEnrollmentCount(){
        return enrollmentRepo.count();
    }

   public Long getExpireEnrollment(){
        return enrollmentRepo.countByExpiresAtBefore(LocalDateTime.now());
   }
   public Long getActiveEnrollment(){
        return enrollmentRepo.countByExpiresAtAfter(LocalDateTime.now());
   }
   public Long getRecentEnrollment(){
        return enrollmentRepo.countBypurchasedAtAfter(LocalDateTime.now().minusDays(7));
   }
   public AdminDashBoardDto getAdminDashboard(){

        return new AdminDashBoardDto(this.getAllUserCount(),this.getAllCourseCount(),this.getAllEnrollmentCount(),
                this.getExpireEnrollment(),this.getActiveEnrollment(),this.getRecentEnrollment());

   }
   public Page<GetAllUserRequest> getUserALl(int page, int size){
        Page<User> users=userRepo.findAll(PageRequest.of(page,size));
       Page<GetAllUserRequest> allusers=users.map(user->{
         return    new GetAllUserRequest(user.getId(),user.getEmail(),user.getRole().name());
       });

       return allusers;

   }
   public Page<Enrollment> getAllEnrollments(int page,int size){
        Page<Enrollment> enrollments=enrollmentRepo.findAll(PageRequest.of(page,size, Sort.by("purchasedAt").ascending()));
        return enrollments;
   }

   @Transactional
  public void deleteUserByid(Long userid){
   User user=userRepo.findById(userid).orElseThrow(()->{
       throw new UserExistException("User not found ");
   });
   user.setIsactive(false);
    userRepo.save(user);
  }
  public String createCourse(String cname,Integer price){
      Course course=courseRepo.save(new Course(cname,price));
      return "Course saved Successfully";
  }

}
