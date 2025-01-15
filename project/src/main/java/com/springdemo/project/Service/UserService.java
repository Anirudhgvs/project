package com.springdemo.project.Service;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<UserEntry> getAllusers() {
        return userRepo.findAll();
    }

    public UserEntry createUser(UserEntry userEntry) {
        return userRepo.insert(userEntry);
    }

    public Optional<UserEntry> getByUserName(String userName) {
        return Optional.ofNullable(userRepo.findByUserName(userName));
    }

    public Object updateUser(UserEntry userEntry1) {
        return userRepo.save(userEntry1);
    }

    public void deleteUser(String id) {

    }
}
