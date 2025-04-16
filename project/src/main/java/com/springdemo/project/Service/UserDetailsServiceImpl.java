package com.springdemo.project.Service;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Arrays;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry userEntry = userRepo.findByUserName(username);
        if(userEntry != null){
            UserDetails userDetails = User.builder().username(userEntry.getUserName())
                    .password(userEntry.getPassword())
                    .roles(userEntry.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username" + username);
    }
}
