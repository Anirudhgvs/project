package com.springdemo.project.Controller;


import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;


    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserEntry userEntry){
        try{
            return new ResponseEntity<>(userService.createUser(userEntry), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/health-check")
    public ResponseEntity<?> health(){
        return new ResponseEntity<>("Health is Okay", HttpStatus.OK);
    }


}
