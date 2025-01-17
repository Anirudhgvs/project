package com.springdemo.project.Service;


import com.springdemo.project.Entity.JournalEntry;
import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.JournalEntryRepo;
import com.springdemo.project.Repositories.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    JournalEntryRepo journalEntryRepo;

    @Autowired
    UserRepo userRepo;

    @Transactional(rollbackFor = Exception.class)
    public JournalEntry createEntry(JournalEntry journalEntry, String userName) throws Exception {
        try{
            JournalEntry savedEntry = journalEntryRepo.insert(journalEntry);
            UserEntry userEntry = userRepo.findByUserName(userName);
            userEntry.getJournalEntries().add(savedEntry);
            userEntry.setUserName(null);
            userRepo.save(userEntry);
            return savedEntry;
        } catch (Exception e){
            // Handle exception (logging, re-throwing, etc.)
            throw new Exception("Error occurred while creating journal entry", e);
        }
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntry(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public JournalEntry updateEntry(JournalEntry journalEntry1) {
        return journalEntryRepo.save(journalEntry1);
    }

    public void deleteEntry(ObjectId id, String userName) {
        UserEntry userEntry = userRepo.findByUserName(userName);
        List<JournalEntry> list = userEntry.getJournalEntries();
        JournalEntry journalEntry = journalEntryRepo.findById(id).get();
        list.remove(journalEntry);
        userEntry.setJournalEntries(list);
        userRepo.save(userEntry);

        journalEntryRepo.deleteById(id);
    }
}
