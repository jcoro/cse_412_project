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

    @Column(name = "NutrDesc")
    private String nutrDesc;

    @Column(name = "Units")
    private String unit;

    public String getNutrDesc() {
        return nutrDesc;
    }

    public void setNutrDesc(String nutrDesc) {
        this.nutrDesc = nutrDesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

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
