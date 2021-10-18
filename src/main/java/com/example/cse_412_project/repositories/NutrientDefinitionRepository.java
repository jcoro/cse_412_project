package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.NutrientDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutrientDefinitionRepository extends CrudRepository<NutrientDefinition, Integer> {
    Optional<NutrientDefinition> findByNutrNo(int nutrNo);
}
