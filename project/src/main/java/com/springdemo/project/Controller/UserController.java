package com.springdemo.project.Controller;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public ResponseEntity<?> getUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntry> userEntry = userService.getByUserName(userName);
        if(userEntry.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userEntry, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser( @RequestBody UserEntry userEntry){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntry> optionalUserEntry = userService.getByUserName(userName);
        UserEntry userEntry1 = optionalUserEntry.get();
        if(userEntry1 != null){
            userEntry1.setUserName(userEntry.getUserName());
            userEntry1.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            return new ResponseEntity<>(userService.updateUser(userEntry1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUserByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
