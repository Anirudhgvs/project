package com.springdemo.project.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class UserEntry {

    @Id
    ObjectId id;
    @Indexed(unique = true)
    @NonNull
    String userName;
    String password;
    @DBRef
    List<JournalEntry> journalEntries = new ArrayList<>();
}
