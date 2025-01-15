package com.springdemo.project.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    ObjectId id;

    @NonNull
    String title;

    @NonNull
    String content;

}
