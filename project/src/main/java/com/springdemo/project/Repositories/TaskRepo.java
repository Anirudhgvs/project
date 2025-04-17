package com.springdemo.project.Repositories;

import com.springdemo.project.Entity.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepo extends MongoRepository<Task, ObjectId> {
}
