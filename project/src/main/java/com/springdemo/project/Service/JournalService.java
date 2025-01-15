package com.springdemo.project.Service;


import com.springdemo.project.Entity.JournalEntry;
import com.springdemo.project.Repositories.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    JournalEntryRepo repo;


    public JournalEntry createEntry(JournalEntry journalEntry) {
        return repo.insert(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return repo.findAll();
    }

    public Optional<JournalEntry> getEntry(ObjectId id) {
        return repo.findById(id);
    }

    public JournalEntry updateEntry(JournalEntry journalEntry1) {
        return repo.save(journalEntry1);
    }

    public void deleteEntry(ObjectId id) {
        repo.deleteById(id);
    }
}
