package com.example.cse_412_project.service;

import com.example.cse_412_project.entities.FoodDescription;
import java.util.List;

public interface ParseDataService {
    List<FoodDescription> parseFoodDescription(String filePath);
}
