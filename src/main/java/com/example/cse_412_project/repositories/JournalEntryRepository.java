package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.JournalEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalEntryRepository extends CrudRepository<JournalEntry, Integer> {

    Optional<List<JournalEntry>> findAllByUsername(String username);

}
