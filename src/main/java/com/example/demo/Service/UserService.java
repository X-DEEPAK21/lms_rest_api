package com.example.demo.Service;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Userid;
import com.example.demo.Exception.CollegeIdIsAlreadyPresentException;
import com.example.demo.Exception.InvalidCollegeIdException;
import com.example.demo.Exception.UserExistException;
import com.example.demo.Repository.UserIdRepo;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserIdRepo userIdRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(User user) {

      Optional<Userid> optional= userIdRepo.findByuserid(user.getCollegeid());
      if(optional.isEmpty())throw new InvalidCollegeIdException("Invalid College Id");

       Userid optuser=optional.get();
      Optional<User> optional1 =userRepo.findByCollegeid(optuser.getUserid());
      if(optional1.isPresent())throw new CollegeIdIsAlreadyPresentException("College id Already Present");

       Userid userid=optional.get();
       user.setCollegeid(userid.getUserid());
       user.setRole(userid.getRole());
       String password=passwordEncoder.encode(user.getPassword());
       user.setPassword(password);
       return  userRepo.save(user);

    }
    @Transactional
    public User saveUserWhilePasswordReset(Long userid,String newpass){
     Optional<User> optional = userRepo.findById(userid);
     if(optional.isEmpty())throw new UserExistException("User not find according to this id");
       User user=optional.get();
       String newPassword=passwordEncoder.encode(newpass);
       user.setPassword(newPassword);
      User user1= userRepo.save(user);
      return user1;

    }

    public User findUserByEmail(String username) {
      Optional<User> optional= userRepo.findByEmail(username);
      if(optional.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");
      return optional.get();

    }

    public User findUserById(Long id){
       Optional<User> user= userRepo.findById(id);
       if(user.isEmpty())throw new UsernameNotFoundException("User doesn't exist");
       return user.get();

    }

    @Transactional
    public User updateEmailOfUser(String email,User user){
        user.setEmail(email);
       User user1= userRepo.save(user);
       return user1;

    }


    }


