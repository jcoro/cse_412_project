package com.example.cse_412_project.mapper;

import com.example.cse_412_project.DTO.JournalEntryDto;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel="spring")
public abstract class JournalEntryMapper {

    @Autowired
    private UserRepository userRepository;

    @Mapping(target="username", source="journalEntryDto.username")
    @Mapping(target="amount", source="journalEntryDto.amount")
    @Mapping(target="journalDate", source="journalEntryDto.journalDate")
    @Mapping(target="orderIndex", source="journalEntryDto.orderIndex")
    @Mapping(target="ndbNo", source="journalEntryDto.ndbNo")
    @Mapping(target="seq", source="journalEntryDto.seq")
    @Mapping(target="user", expression="java(getUser(journalEntryDto.getUsername()))")
    public abstract JournalEntry mapJournalEntryDTOtoJournalEntry(JournalEntryDto journalEntryDto);

    AppUser getUser(String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        return user.get();
    }
}