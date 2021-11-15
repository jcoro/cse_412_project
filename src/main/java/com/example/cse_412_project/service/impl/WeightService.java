package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.Weight;
import com.example.cse_412_project.repositories.WeightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class WeightService {

    private final WeightRepository weightRepository;

    public WeightService(final WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public void save(final Weight weight) {
        weightRepository.save(weight);
    }
}
