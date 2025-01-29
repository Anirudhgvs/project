package com.springdemo.project.Service;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        //To simulate the call made for Database you are using the below line
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(UserEntry.builder().userName("ram").password("ram").roles(Collections.singletonList("USER")).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("shyam");
        Assertions.assertNotNull(userDetails);
    }

    @Test
    void loadUserByUsernameTestNeg(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("shyam"));
    }
}
