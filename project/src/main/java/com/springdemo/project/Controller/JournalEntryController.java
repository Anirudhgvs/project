package com.springdemo.project.Controller;

import com.springdemo.project.Entity.JournalEntry;
import com.springdemo.project.Entity.UserEntry;
import com.springdemo.project.Service.JournalService;
import com.springdemo.project.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

   @Autowired
   JournalService journalService;

   @Autowired
   UserService userService;


    @GetMapping
    public ResponseEntity<?> getAllEntriesOfUser(){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntry user = userService.getByUserName(userName).get();
            return new ResponseEntity<>(user.getJournalEntries(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEntryByIdOfUser(@PathVariable ObjectId id){
//        Optional<JournalEntry> entry = journalService.getEntry(id);
//        if(!entry.isEmpty()){
//            return new ResponseEntity<>(entry, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/entry")
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry journalEntry){
       try{
           String userName = SecurityContextHolder.getContext().getAuthentication().getName();
           return new ResponseEntity<>(journalService.createEntry(journalEntry, userName), HttpStatus.CREATED);
       } catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<JournalEntry> oldEntry = null;
            oldEntry = journalService.getEntryOfUser(id, userName);
            JournalEntry oldEntry1 = oldEntry.get();
            if(oldEntry1 != null){
                oldEntry1.setContent(journalEntry.getContent());
                oldEntry1.setTitle(journalEntry.getTitle());
                return new ResponseEntity<>(journalService.updateEntry(oldEntry1), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        journalService.deleteEntry(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
