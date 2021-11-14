package com.example.cse_412_project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FD_GROUP")
public class FoodGroup {

    @Id
    @Column(name = "FdGrp_Cd", nullable = false)
    private int foodGrpCode;

    @Column(name = "FdGrp_Desc")
    private String foodGrpDesc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodGrpCode", cascade = CascadeType.DETACH)
    private List<FoodDescription> foodDescriptionList;

    public int getFoodGrpCode() {
        return foodGrpCode;
    }

    public void setFoodGrpCode(int foodGrpCode) {
        this.foodGrpCode = foodGrpCode;
    }

    public String getFoodGrpDesc() {
        return foodGrpDesc;
    }

    public void setFoodGrpDesc(String foodGrpDesc) {
        this.foodGrpDesc = foodGrpDesc;
    }

    public List<FoodDescription> getFoodDescriptionList() {
        return foodDescriptionList;
    }

    public void setFoodDescriptionList(List<FoodDescription> foodDescriptionList) {
        this.foodDescriptionList = foodDescriptionList;
    }
}
