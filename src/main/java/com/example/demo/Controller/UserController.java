package com.example.demo.Controller;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.User;
import com.example.demo.Exception.UserExistException;
import com.example.demo.Repository.UserRepo;
import com.example.demo.RequestDto.*;
import com.example.demo.ResponseDto.*;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.ProfileService;
import com.example.demo.Service.UserService;
import com.example.demo.SpringSecurityDetail.Token;
import com.example.demo.SpringSecurityDetail.UserDetailImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProfileService profileService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Token token;
    @Autowired
    CourseService courseService;




    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody User user){

        Optional<User>user1=userRepo.findByEmail(user.getEmail());
        if(user1.isPresent()){
            throw new UserExistException("Already exist");
        }
        User user2=userService.saveUser(user);
        user2.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponseDto(user2,"Successfully Registered"));

    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword());
        System.out.println(loginRequestDto);
        Authentication authentication=authenticationManager.authenticate(usernamePasswordAuthenticationToken);

       User user=((UserDetailImpl)authentication.getPrincipal()).getUser();
        System.out.println(user);
       String jwt_token=token.generateAccessToken(user);
       return ResponseEntity.status(HttpStatus.CREATED).
               body(new LoginResponseDto(jwt_token,"Verified Successfully","Bearer",user.getEmail(),user.getRole(),"30Min"));

    }
    @PostMapping("/forgot-password")
    public ResponseEntity<FindEmail> getEmail(String email){
        User user=userService.findUserByEmail(email);
        FindEmail findEmail=new FindEmail(user.getId(),user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(findEmail);

    }

    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResponseDto> resetPassword(@Valid @RequestBody PasswordRequest passwordRequest){
          User user= userService.saveUserWhilePasswordReset(passwordRequest.getUserId(),passwordRequest.getPassword());

          return ResponseEntity.status(HttpStatus.ACCEPTED)
                  .body(new PasswordResponseDto("Password Reset SuccessFully"));

    }


    @GetMapping("/login/student/profile")
    public ResponseEntity<ProfileResponseDto>  getProfile(){

    UserDetailImpl userDetail=(UserDetailImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user=userDetail.getUser();


    return ResponseEntity.status(HttpStatus.CREATED).
            body(profileService.getProfile(user));
    }

    @PatchMapping("/login/student/profile/update-email")
    public ResponseEntity<EmailUpdateResponseDto> updateEmail(@Valid @RequestBody UpdateEmailRequestDto updateEmailRequestDto){
        UserDetailImpl userDetail=(UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=userDetail.getUser();
       User user1 =userService.updateEmailOfUser(updateEmailRequestDto.getEmail(), user);
        EmailUpdateResponseDto emailUpdateResponseDto=new EmailUpdateResponseDto(user1.getEmail(),"Updated Successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(emailUpdateResponseDto);
        
    }

   @PostMapping("/login/student/profile/enroll")
    public ResponseEntity<CourseResponseDto> enrollCourse(@RequestBody EnrollRequestDto enrollRequestDto){

      UserDetailImpl userDetail=(UserDetailImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

     Enrollment enrollment= courseService.enrollByCourseId(userDetail.getUser(),enrollRequestDto.getCourseId());
     String status=courseService.getStatus(enrollment.getExpiresAt());
     CourseResponseDto courseResponseDto=
             new CourseResponseDto(enrollment.getCourseId().getName(),"Purchased Successfully",enrollment.getCourseId().getId(),enrollment.getExpiresAt(),status);

     return  ResponseEntity.status(HttpStatus.ACCEPTED).body(courseResponseDto);
    }

    @GetMapping("/student/profile/get")// courses he purchased
    public ResponseEntity<List<CourseDetailsResponseDto>> myCourses(){

       UserDetailImpl userDetail=(UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       User user=userDetail.getUser();
      List<Enrollment> enrollments= courseService.getEnrollments(user.getId());
      List<CourseDetailsResponseDto> courseResponseDtoList= courseService.getCourseResponse(enrollments);
      return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDtoList);

    }


}
