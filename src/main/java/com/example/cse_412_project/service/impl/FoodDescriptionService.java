package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.entities.Weight;
import com.example.cse_412_project.factory.impl.LoadInitialData;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Optional;

@Transactional
@Service
public class FoodDescriptionService {

    private final FoodDescriptionRepository foodDescriptionRepository;
    private final WeightService weightService;

    private static final Logger logger = LoggerFactory.getLogger(FoodDescriptionService.class);

    @Autowired
    public FoodDescriptionService(final FoodDescriptionRepository foodDescriptionRepository,
                                  final WeightService weightService) {
        this.foodDescriptionRepository = foodDescriptionRepository;
        this.weightService = weightService;
    }

    public Optional<FoodDescription> findByNdbNo(final int ndbNo) {
        return foodDescriptionRepository.findByNdbNo(ndbNo);
    }

    public void save(final FoodDescription foodDescription) {
        foodDescriptionRepository.save(foodDescription);
    }

    public void addWeight(final Weight weight) {
        Optional<FoodDescription> tempFoodDescription = findByNdbNo(weight.getWeightKey().getNdbNo());
        if (!tempFoodDescription.isPresent()) {
            logger.error(String.format("Food description with nbdNo (%d) does not exist", weight.getWeightKey().getNdbNo()));
        } else {
            FoodDescription foodDescription = tempFoodDescription.get();
            weight.setFoodDescription(foodDescription);
            if (foodDescription.getWeights() == null) {
                foodDescription.setWeights(new LinkedList<>());
            }
            foodDescription.getWeights().add(weight);
            weightService.save(weight);
//                foodDescriptionRepository.save(foodDescription);
        }
    }

    public void addNutrientData(final NutrientData nutrientData) {
        Optional<FoodDescription> tempFoodDescription = findByNdbNo(nutrientData.getNutDataKey().getNdbNo());
        FoodDescription foodDescription = tempFoodDescription.get();
        nutrientData.setFoodDescription(foodDescription);
        if (foodDescription.getNutDataList() == null) {
            foodDescription.setNutDataList(new LinkedList<>());
        }

        foodDescription.getNutDataList().add(nutrientData);
    }
}
