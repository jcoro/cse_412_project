package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.repositories.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    @Autowired
    public JournalEntryService(JournalEntryRepository journalEntryRepository){
        this.journalEntryRepository = journalEntryRepository;
    }

    public void save(JournalEntryDto journalEntryDto){
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setUsername(journalEntryDto.getUsername());
        journalEntry.setAmount(journalEntryDto.getAmount());
        journalEntry.setJournalDate(journalEntryDto.getJournalDate());
        journalEntry.setOrderIndex(journalEntryDto.getOrderIndex());
        journalEntry.setNdbNo(journalEntryDto.getNdbNo());
        journalEntry.setSeq(journalEntryDto.getSeq());
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> getAllByUsername(String username){
        return journalEntryRepository.findAllByUsername(username);
    }

    public List<JournalEntry> getAll(){
        return (List<JournalEntry>) journalEntryRepository.findAll();
    }


}
