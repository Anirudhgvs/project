package com.springdemo.project.Repositories;

import com.springdemo.project.Entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntry, ObjectId> {

    UserEntry findByUserName(String userName);

}
