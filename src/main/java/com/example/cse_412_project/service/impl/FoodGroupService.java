package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.FoodGroup;
import com.example.cse_412_project.repositories.FoodGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Optional;

@Transactional
@Service
public class FoodGroupService {

    private final FoodGroupRepository foodGroupRepository;
    private final FoodDescriptionService foodDescriptionService;

    private static final Logger logger = LoggerFactory.getLogger(FoodGroupService.class);

    @Autowired
    public FoodGroupService(final FoodGroupRepository foodGroupRepository,
                            final FoodDescriptionService foodDescriptionService) {
        this.foodGroupRepository = foodGroupRepository;
        this.foodDescriptionService = foodDescriptionService;
    }

    public long count() {
        return foodGroupRepository.count();
    }

    public Optional<FoodGroup> findByFoodGrpCode(final int foodGrpCode) {
        return foodGroupRepository.findByFoodGrpCode(foodGrpCode);
    }

    public void save(final FoodGroup foodGroup) {
        foodGroupRepository.save(foodGroup);
    }

    public void addFoodDescription(final FoodDescription foodDes) {
        int foodGrpCode = foodDes.getFoodGrpCode();
        Optional<FoodGroup> tempFoodGroup = findByFoodGrpCode(foodGrpCode);
        if (!tempFoodGroup.isPresent()) {
            logger.error(String.format("Food group with foodGrpCode (%d) does not exist", foodGrpCode));
        } else {
            FoodGroup foodGroup = tempFoodGroup.get();
            foodDes.setFoodGroup(foodGroup);
            if (foodGroup.getFoodDescriptionList() == null) {
                foodGroup.setFoodDescriptionList(new LinkedList<>());
            }
            foodGroup.getFoodDescriptionList().add(foodDes);
            foodDescriptionService.save(foodDes);
//                    foodGroupRepository.save(foodGroup);
        }
    }
}
