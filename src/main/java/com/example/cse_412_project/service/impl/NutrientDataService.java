package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.repositories.NutrientDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NutrientDataService {
    private final NutrientDataRepository nutrientDataRepository;

    @Autowired
    public NutrientDataService(final NutrientDataRepository nutrientDataRepository) {
        this.nutrientDataRepository = nutrientDataRepository;
    }

    public void save(final NutrientData nutrientData) {
        nutrientDataRepository.save(nutrientData);
    }
}
