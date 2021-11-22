package com.example.cse_412_project.controllers;

import com.example.cse_412_project.DTO.FoodDescriptionDto;
import com.example.cse_412_project.entities.FoodGroup;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.example.cse_412_project.repositories.FoodGroupRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class FoodGroupController {
    private final FoodGroupRepository foodGroupRepository;

    public FoodGroupController(FoodGroupRepository foodGroupRepository) {
        this.foodGroupRepository = foodGroupRepository;
    }

    @GetMapping("/foodgroups")
    public List<FoodGroup> getFoodGroups() {
        return foodGroupRepository.findAll();
    }
}
