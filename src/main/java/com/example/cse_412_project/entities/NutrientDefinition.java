package com.example.cse_412_project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class NutrientDefinition {

    @Id
    @Column(name = "Nutr_No", nullable = false)
    private int nutrNo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nutrientDefinition", cascade = CascadeType.DETACH)
    private List<NutrientData> nutDataList;

    // todo: Completed the rest of the columns

    public int getNutrNo() {
        return nutrNo;
    }

    public void setNutrNo(int nutrNo) {
        this.nutrNo = nutrNo;
    }

    public List<NutrientData> getNutDataList() {
        return nutDataList;
    }

    public void setNutDataList(List<NutrientData> nutDataList) {
        this.nutDataList = nutDataList;
    }
}
