package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.DTO.JournalEntryResponse;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.mapper.JournalEntryMapper;
import com.example.cse_412_project.repositories.JournalEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryMapper journalEntryMapper;

    public JournalEntry save(JournalEntryDto journalEntryDto){
        return journalEntryRepository.save(journalEntryMapper.mapJournalEntryDTOtoJournalEntry(journalEntryDto));
    }

    public Optional<List<JournalEntry>> getAllByUsername(String username){
        return journalEntryRepository.findAllByUsername(username);
    }

    public List<JournalEntry> getAll(){
        return (List<JournalEntry>) journalEntryRepository.findAll();
    }
    public List<JournalEntryResponse> findAllDtoWithUsername(String username) {
        return journalEntryRepository.findAllDtoWithUsername(username);
    }
}
