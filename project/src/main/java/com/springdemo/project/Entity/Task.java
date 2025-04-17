package com.springdemo.project.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    ObjectId id;

    String title;

    String description;

    Date dueDate;

    Status status;

    Priority priority;

    @DBRef
    User assignedTo;

    @DBRef
    User createdBy;
}
