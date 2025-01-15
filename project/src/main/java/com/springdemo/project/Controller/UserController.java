package com.springdemo.project.Controller;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            List<UserEntry> list = userService.getAllusers();
            if (list != null && !list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntry userEntry){
        try{
            return new ResponseEntity<>(userService.createUser(userEntry), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody UserEntry userEntry){
        Optional<UserEntry> optionalUserEntry = userService.getByUserName(userName);
        UserEntry userEntry1 = optionalUserEntry.get();
        if(userEntry1 != null){
            userEntry1.setUserName(userEntry.getUserName());
            userEntry1.setPassword(userEntry.getPassword());
            return new ResponseEntity<>(userService.updateUser(userEntry1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
