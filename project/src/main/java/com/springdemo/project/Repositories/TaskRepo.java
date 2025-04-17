package com.springdemo.project.Repositories;

import com.springdemo.project.Entity.Task;
import com.springdemo.project.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepo extends MongoRepository<Task, ObjectId> {

    List<Task> findByAssignedTo(User user);

    void deleteById(ObjectId id);
}
