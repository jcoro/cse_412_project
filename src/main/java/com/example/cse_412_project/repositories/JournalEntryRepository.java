package com.example.cse_412_project.repositories;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.entities.JournalEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalEntryRepository extends CrudRepository<JournalEntry, Integer> {

    Optional<JournalEntry> findAllByUsername(String username);

    @Query("SELECT new com.example.cse_412_project.DTO.JournalEntryDto(je.username, je.amount, je.journalDate, je.orderIndex, je.ndbNo, je.seq) FROM JournalEntry je")
    List<JournalEntryDto> findAllDto();
}
