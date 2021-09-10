package com.example.cse_412_project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NutrientDefinition {

    @Id
    @Column(name = "Nutr_No", nullable = false)
    private int Nutr_No;
}
