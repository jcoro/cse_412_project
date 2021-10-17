package com.example.cse_412_project.entities;


import javax.persistence.*;

@Entity
@Table(name = "NUT_DATA")
public class NutrientData {
    @EmbeddedId
    private NutDataKey nutDataKey;

    @Column(name = "Nutr_Val")
    private float nutrVal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "NDB_No", updatable = false)
    @MapsId("ndbNo")
    private FoodDescription foodDescription;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "Nutr_No", updatable = false)
    @MapsId("nutrNo")
    private NutrientDefinition nutrientDefinition;

    public NutDataKey getNutDataKey() {
        return nutDataKey;
    }

    public void setNutDataKey(NutDataKey nutDataKey) {
        this.nutDataKey = nutDataKey;
    }

    public float getNutrVal() {
        return nutrVal;
    }

    public void setNutrVal(float nutrVal) {
        this.nutrVal = nutrVal;
    }

    public FoodDescription getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(FoodDescription foodDescription) {
        this.foodDescription = foodDescription;
    }

    public NutrientDefinition getNutrientDefinition() {
        return nutrientDefinition;
    }

    public void setNutrientDefinition(NutrientDefinition nutrientDefinition) {
        this.nutrientDefinition = nutrientDefinition;
    }
}
