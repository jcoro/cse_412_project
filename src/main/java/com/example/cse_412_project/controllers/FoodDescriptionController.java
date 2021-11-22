package com.example.cse_412_project.controllers;

import com.example.cse_412_project.DTO.FoodDescriptionDto;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class FoodDescriptionController {

    private final FoodDescriptionRepository foodDescriptionRepository;

    public FoodDescriptionController(FoodDescriptionRepository foodDescriptionRepository) {
        this.foodDescriptionRepository = foodDescriptionRepository;
    }

    @GetMapping("/foods")
    public List<FoodDescriptionDto> getFoods() {
        return foodDescriptionRepository.findAllDto();
    }
}
