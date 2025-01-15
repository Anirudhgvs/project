package com.springdemo.project.Controller;

import com.springdemo.project.Entity.JournalEntry;
import com.springdemo.project.Service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

   @Autowired
   JournalService journalService;

   //Fetch resources
   @GetMapping
   public ResponseEntity<?> getAllEntries(){
       try {
           List<JournalEntry> list = journalService.getAllEntries();
           if (list != null && !list.isEmpty()) {
               return new ResponseEntity<>(list, HttpStatus.OK);
           }
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId id){
        Optional<JournalEntry> entry = journalService.getEntry(id);
        if(!entry.isEmpty()){
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/entry")
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry journalEntry){
       try{
           return new ResponseEntity<>(journalService.createEntry(journalEntry), HttpStatus.CREATED);
       } catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        Optional<JournalEntry> optionalJournalEntry = journalService.getEntry(id);
        JournalEntry journalEntry1 = optionalJournalEntry.get();
        if(journalEntry1 != null){
            journalEntry1.setContent(journalEntry.getContent());
            journalEntry1.setTitle(journalEntry.getTitle());
            return new ResponseEntity<>(journalService.updateEntry(journalEntry1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntriy(@PathVariable ObjectId id){
        journalService.deleteEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
