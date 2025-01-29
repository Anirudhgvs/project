package com.springdemo.project.Service;


import com.springdemo.project.Entity.JournalEntry;
import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Repositories.JournalEntryRepo;
import com.springdemo.project.Repositories.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            userRepo.save(userEntry);
            return savedEntry;
        } catch (Exception e){
            throw new Exception("Error occurred while creating journal entry", e);
        }
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntryOfUser(ObjectId id, String userName) throws Exception {
        UserEntry userEntry = userRepo.findByUserName(userName);
        if(userEntry != null) {
            List<JournalEntry> entries = userEntry.getJournalEntries();
            List<JournalEntry> matchedEntryList = entries.stream().filter(e -> e.getId().toString().equals(id.toString())).collect(Collectors.toList());
            JournalEntry matchedEntry = matchedEntryList.get(0);
            return Optional.ofNullable(matchedEntry);
        }else {
            throw new Exception("Entry not found for the User");
        }
    }

    public JournalEntry updateEntry(JournalEntry journalEntry1) {
        return journalEntryRepo.save(journalEntry1);
    }

    @Transactional
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
