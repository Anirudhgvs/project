package com.springdemo.project.Controller;

import com.springdemo.project.Entity.User;
import com.springdemo.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User request) {
        userService.createUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<User> fetchUser(@RequestBody User request) {
        Optional<User> optionalUser = userService.getByUserName(request.getUsername());
        return optionalUser.map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
