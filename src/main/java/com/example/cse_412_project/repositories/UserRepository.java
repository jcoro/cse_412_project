package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.impl.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UserRepository.java - DESCRIPTION
 * Author: John Coronite
 * Date: 8/29/21
 **/

public interface UserRepository extends PagingAndSortingRepository<AppUser, String> {
    AppUser findByEmail(String email);
}
