package com.example.cse_412_project.service;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.exceptions.FoodDescriptionNotFoundException;

import java.util.List;

public interface ParseDataService {
    List<FoodDescription> parseFoodDescription(String filePath);

    List<NutrientData> parseNutrientData(String filePath) throws FoodDescriptionNotFoundException;
}
