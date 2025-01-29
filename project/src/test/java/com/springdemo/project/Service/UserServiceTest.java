package com.springdemo.project.Service;

import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.UserRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @BeforeAll
    static void setUp(){
        System.out.println("Before All");
    }

    @AfterAll
    static void closing(){
        System.out.println("After All");
    }


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testgetByUserName(UserEntry userEntry){
        //Get journal entries and return false if it is empty, return true if any journal entries are present
        //assert true means check if true
        assertTrue(userService.createUser(userEntry));
    }


    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,3,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }

}
