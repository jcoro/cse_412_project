package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.FoodGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodGroupRepository extends CrudRepository<FoodGroup, Integer> {
    Optional<FoodGroup> findByFoodGrpCode (int foodGrpCode);
}
