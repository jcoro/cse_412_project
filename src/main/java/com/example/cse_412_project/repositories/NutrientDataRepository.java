package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.NutDataKey;
import com.example.cse_412_project.entities.NutrientData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NutrientDataRepository extends CrudRepository<NutrientData, String> {
    Optional<NutrientData> findByNutDataKey(NutDataKey nutDataKey);
}
