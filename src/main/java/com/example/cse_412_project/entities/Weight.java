package com.example.cse_412_project.entities;

import javax.persistence.*;

@Entity
@Table(name="WEIGHT")
public class Weight {
    @EmbeddedId
    private WeightKey weightKey;

    @Column(name = "Gm_Wgt")
    private float gramWeight;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Msre_Desc")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "NDB_No", updatable = false)
    @MapsId("ndbNo")
    private FoodDescription foodDescription;

    public WeightKey getWeightKey() {
        return weightKey;
    }

    public void setWeightKey(WeightKey weightKey) {
        this.weightKey = weightKey;
    }

    public float getGramWeight() {
        return gramWeight;
    }

    public void setGramWeight(float gramWeight) {
        this.gramWeight = gramWeight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodDescription getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(FoodDescription foodDescription) {
        this.foodDescription = foodDescription;
    }
}
