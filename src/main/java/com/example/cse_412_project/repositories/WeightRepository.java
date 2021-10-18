package com.example.cse_412_project.repositories;

import com.example.cse_412_project.entities.Weight;
import com.example.cse_412_project.entities.WeightKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeightRepository extends CrudRepository<Weight, WeightKey> {
    Optional<Weight> getByWeightKey(WeightKey weightKey);
}
