package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.entities.NutrientDefinition;
import com.example.cse_412_project.repositories.NutrientDataRepository;
import com.example.cse_412_project.repositories.NutrientDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Optional;

@Transactional
@Service
public class NutrientDefinitionService {
    private final NutrientDefinitionRepository nutrientDefinitionRepository;

    @Autowired
    public NutrientDefinitionService(final NutrientDefinitionRepository nutrientDefinitionRepository) {
        this.nutrientDefinitionRepository = nutrientDefinitionRepository;
    }

    public Optional<NutrientDefinition> findByNutrNo(final int nutrNo) {
        return nutrientDefinitionRepository.findByNutrNo(nutrNo);
    }

    public void save(final NutrientDefinition nutrientDefinition) {
        nutrientDefinitionRepository.save(nutrientDefinition);
    }

    public void addNutrientData(final NutrientData nutrientData) {
        Optional<NutrientDefinition> tempNutrientDefinition = findByNutrNo(nutrientData.getNutDataKey().getNutrNo());
        NutrientDefinition nutrientDefinition = tempNutrientDefinition.get();
        nutrientData.setNutrientDefinition(nutrientDefinition);

        if (nutrientDefinition.getNutDataList() == null) {
            nutrientDefinition.setNutDataList(new LinkedList<>());
        }
        nutrientDefinition.getNutDataList().add(nutrientData);
    }
}
