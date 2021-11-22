package com.example.cse_412_project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NutrientDataDto {

    @JsonProperty("nutrNo")
    private int nutrNo;

    @JsonProperty("nutrVal")
    private float nutrVal;

    public NutrientDataDto(int nutrNo, float nutrVal){
        this.nutrNo = nutrNo;
        this.nutrVal = nutrVal;
    }

    public int getNutrNo() {
        return nutrNo;
    }

    public void setNutrNo(int nutrNo) {
        this.nutrNo = nutrNo;
    }

    public float getNutrVal() {
        return nutrVal;
    }

    public void setNutrVal(float nutrVal) {
        this.nutrVal = nutrVal;
    }
}
