package com.example.demo.Controller;

import com.example.demo.Entity.Enrollment;
import com.example.demo.ResponseDto.AdminDashBoardDto;
import com.example.demo.ResponseDto.GetAllUserRequest;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/auth")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login/admin/dashboard")
    public ResponseEntity<AdminDashBoardDto> getAdminProfile(){
     AdminDashBoardDto adminDashBoardDto= adminService.getAdminDashboard();
     return ResponseEntity.status(HttpStatus.CREATED).body(adminDashBoardDto);
    }
    @GetMapping("/login/admin/get-all")
    public ResponseEntity<Page<GetAllUserRequest>> getAllUsers(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "10") int size){
     return ResponseEntity.status(HttpStatus.CREATED)
             .body(adminService.getUserALl(page,size));
    }

    @PostMapping("/login/admin/create-course")
    public ResponseEntity<String> createCourse(@RequestParam String name,@RequestParam Integer price){
       String response= adminService.createCourse(name,price);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/login/admin/getall-Enrollment")
    public ResponseEntity<Page<Enrollment> > getAllEnrollment(@RequestParam int page,@RequestParam int size){
        Page<Enrollment> enrollments= adminService.getAllEnrollments(page,size);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollments);
        }

        @PostMapping("login/admin/dashboard/{userId}")
     public ResponseEntity<Map<String,String>> deleteUser(@PathVariable long userId){
        adminService.deleteUserByid(userId);
         Map<String,String> value=new HashMap<>();
            value.put("message","Successfully Deleted");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(value);

        }



}
