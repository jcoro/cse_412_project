package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.FoodGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodGroupRepository extends CrudRepository<FoodGroup, Integer> {
    Optional<FoodGroup> findByFoodGrpCode (int foodGrpCode);

    @Query("SELECT new FoodGroup(fg.foodGrpCode, fg.foodGrpDesc) FROM FoodGroup fg")
    List<FoodGroup> findAll();
}
