package com.example.cse_412_project.controllers;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.service.impl.JournalEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping("/journalentries/{username}")
    public Optional<JournalEntry> getJournalEntries(@PathVariable("username") String username) {
        return journalEntryService.getAllByUsername(username);
    }

    @GetMapping("/journalentries")
    public List<JournalEntry> getJournalEntry() {
        return journalEntryService.getAll();
    }

    @PostMapping("/journalentries")
    public void saveJournalEntry(@RequestBody JournalEntryDto journalEntryDto) {
        journalEntryService.save(journalEntryDto);
    }
}
