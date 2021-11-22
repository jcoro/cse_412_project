package com.example.cse_412_project.controllers;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.DTO.JournalEntryResponse;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.service.impl.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping("/journalentries/{username}")
    public List<JournalEntryResponse> getJournalEntries(@PathVariable("username") String username) {
        return journalEntryService.getAllByUsername(username);
    }

    @GetMapping("/journalentries")
    public List<JournalEntry> getJournalEntry() {
        return journalEntryService.getAll();
    }

    @PostMapping("/journalentries")
    public JournalEntryResponse saveJournalEntry(@RequestBody JournalEntryDto journalEntryDto) {
        return journalEntryService.save(journalEntryDto);
    }

    @PostMapping("/journalentries/{id}")
    public JournalEntryResponse updateJournalEntry(@PathVariable("id") int id,
                                                   @RequestBody JournalEntryDto journalEntryDto){
        return journalEntryService.updateJournalEntry(id, journalEntryDto);
    }

    @PostMapping("/journalentries/{id}/delete")
    public void deleteJournalEntry(@PathVariable("id") int id,
                                   @RequestBody JournalEntryDto journalEntryDto){
        journalEntryService.deleteJournalEntry(id);
    }
}
