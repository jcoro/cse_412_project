package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.FoodDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodDescriptionRepository extends CrudRepository<FoodDescription, Integer> {
    Optional<FoodDescription> findByNdbNo(int ndbNo);
}
