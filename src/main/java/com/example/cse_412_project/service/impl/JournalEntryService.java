package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.DTO.JournalEntryResponse;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.mapper.JournalEntryMapper;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.example.cse_412_project.repositories.JournalEntryRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final FoodDescriptionRepository foodDescriptionRepository;
    private final JournalEntryMapper journalEntryMapper;

    public JournalEntryResponse save(JournalEntryDto journalEntryDto) {
        new JournalEntry();
        LocalDateTime oldDateTime = LocalDateTime.parse(journalEntryDto.getJournalDate().toString());
        ZoneId oldZone = ZoneId.of("Etc/Greenwich");
        ZoneId newZone = ZoneId.systemDefault();
        LocalDateTime newDateTime = oldDateTime.atZone(oldZone)
                .withZoneSameInstant(newZone)
                .toLocalDateTime();
        journalEntryDto.setJournalDate(newDateTime);
        JournalEntry journalEntry;
        // Save the journal entry via the JournalEntryDTO
        journalEntry = journalEntryRepository.save(journalEntryMapper.mapJournalEntryDTOtoJournalEntry(journalEntryDto));
        // then create and return the JournalEntryResponse populated with the info needed by the app front end
        return new JournalEntryResponse(foodDescriptionRepository, journalEntry);
    }

    public List<JournalEntryResponse> getAllByUsername(String username) {
        Optional<List<JournalEntry>> journalEntries = journalEntryRepository.findAllByUsername(username);
        List<JournalEntryResponse> journalEntryResponses = new ArrayList<>();
        journalEntries.ifPresent(list -> list.forEach(e -> journalEntryResponses.add(new JournalEntryResponse(foodDescriptionRepository, e))));
        return journalEntryResponses;
    }

    public JournalEntryResponse updateJournalEntry(int id, JournalEntryDto journalEntryDto){
        JournalEntry journalEntry = journalEntryMapper.mapJournalEntryDTOtoJournalEntry(journalEntryDto);
        Optional<JournalEntry> entry = journalEntryRepository.findById(id);
        JournalEntryResponse journalEntryResponse = null;
        if (entry.isPresent()) {
            entry.get().setUsername(journalEntry.getUsername());
            entry.get().setAmount(journalEntry.getAmount());
            entry.get().setJournalDate(journalEntry.getJournalDate());
            entry.get().setOrderIndex(journalEntry.getOrderIndex());
            entry.get().setNdbNo(journalEntry.getNdbNo());
            entry.get().setSeq(journalEntry.getSeq());
            journalEntryRepository.save(entry.get());
            journalEntryResponse = new JournalEntryResponse(foodDescriptionRepository, entry.get());
        }
        return journalEntryResponse;
    }

    public void deleteJournalEntry(int id){
        Optional<JournalEntry> journalEntry = journalEntryRepository.findById(id);
        if (journalEntry.isPresent()){
            journalEntryRepository.deleteById(id);
        }
    }

    public List<JournalEntry> getAll() {
        return (List<JournalEntry>) journalEntryRepository.findAll();
    }
}
