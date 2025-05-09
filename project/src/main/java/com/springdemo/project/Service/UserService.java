package com.springdemo.project.Service;

import com.springdemo.project.Entity.Role;
import com.springdemo.project.Entity.User;
import com.springdemo.project.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllusers() {
        return userRepo.findAll();
    }

    public Boolean createUser(User userEntry) {
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userRepo.insert(userEntry);
            return true;
        } catch (Exception e){
            return false;
        }
    }

//    public UserEntry createAdminUser(UserEntry userEntry) {
//        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
//        userEntry.setRoles(Collections.singletonList("ADMIN"));
//    }

    public Optional<User> getByUserName(String userName) {
        return Optional.ofNullable(userRepo.findByUsername(userName));
    }

    public Object updateUser(User userEntry1) {
        return userRepo.save(userEntry1);
    }

    public void deleteUserByUserName(String userName) {
        userRepo.deleteByUsername(userName);
    }
}
