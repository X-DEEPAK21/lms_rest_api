package com.example.demo.ExceptionHandle;

import com.example.demo.Exception.CourseNotExist;
import com.example.demo.Exception.InvalidCollegeIdException;
import com.example.demo.Exception.UserExistException;
import com.example.demo.ResponseDto.CourseResponseDto;
import com.example.demo.ResponseDto.RegisterResponseDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class LoginAndRegistationExceptionhandle {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<RegisterResponseDto> error(Exception exception){
        RegisterResponseDto registerResponseDto=new RegisterResponseDto(null,exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(registerResponseDto);
    }
    @ExceptionHandler(value = InvalidCollegeIdException.class)
    public ResponseEntity<GenericResponseDto> invalidCollegeId(InvalidCollegeIdException exception){
        return
               ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body(new GenericResponseDto(exception.getMessage()));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<GenericResponseDto> userNameNotFound(UsernameNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GenericResponseDto(exception.getMessage()));

    }
    //used for Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationErrors(MethodArgumentNotValidException exception1){

        Map<String,String > error=new HashMap<>();
             exception1.getBindingResult()
               .getFieldErrors().forEach(ex->{
                   error.put(ex.getField(),ex.getDefaultMessage());
                     });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

   //RequestBody datatype mismatch
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> requestBodyException(HttpMessageNotReadableException exception){

        Map<String,String> error=new HashMap<>();
        error.put("error","Invalid Request Body");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);

    }
    //Request param Handle
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> ConstraintViolation(MethodArgumentNotValidException exception1){

        Map<String,String > error=new HashMap<>();
        exception1.getBindingResult()
                .getFieldErrors().forEach(ex->{
                    error.put(ex.getDefaultMessage(),ex.getDefaultMessage());
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }



    @ExceptionHandler(value = CourseNotExist.class)
    public ResponseEntity<CourseResponseDto> courseError(Exception exception){

        CourseResponseDto courseResponseDto=new CourseResponseDto(exception.getMessage(), "Failed to enroll");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(courseResponseDto);

    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<String> deleteUserException(LockedException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is blocked By Admin");
    }



}
