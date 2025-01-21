package com.springdemo.project.Service;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserEntry> getAllusers() {
        return userRepo.findAll();
    }

    public UserEntry createUser(UserEntry userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Collections.singletonList("USER"));
        return userRepo.insert(userEntry);
    }

    public Optional<UserEntry> getByUserName(String userName) {
        return Optional.ofNullable(userRepo.findByUserName(userName));
    }

    public Object updateUser(UserEntry userEntry1) {
        return userRepo.save(userEntry1);
    }

    public void deleteUserByUserName(String userName) {
        userRepo.deleteByUserName(userName);
    }
}
