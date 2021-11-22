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
        return journalEntryService.findAllDtoWithUsername(username);
    }

    @GetMapping("/journalentries")
    public List<JournalEntry> getJournalEntry() {
        return journalEntryService.getAll();
    }

    @PostMapping("/journalentries")
    public JournalEntry saveJournalEntry(@RequestBody JournalEntryDto journalEntryDto) {
        return journalEntryService.save(journalEntryDto);
    }
}
