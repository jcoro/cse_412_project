package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.NutrientDefinition;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NutrientDefinitionRepository extends CrudRepository<NutrientDefinition, String> {
    Optional<NutrientDefinition> findByNutrNo(int nutrNo);
}
