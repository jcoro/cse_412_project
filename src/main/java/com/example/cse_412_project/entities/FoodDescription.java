package com.example.cse_412_project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FOOD_DES")
public class FoodDescription {

    @Id
    @Column(name = "NDB_No", nullable = false)
    private int ndbNo;

    @Column(name = "FdGrp_Cd")
    private int foodGrpCode;

    @Column(name = "Long_Desc")
    private String longDesc;

    @Column(name = "Shrt_Desc")
    private String shortDesc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodDescription", cascade = CascadeType.DETACH)
    private List<NutrientData> nutDataList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodDescription", cascade = CascadeType.DETACH)
    private List<Weight> weights;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "FdGrp_Cd", referencedColumnName = "FdGrp_Cd", insertable=false, updatable=false)
    private FoodGroup foodGroup;

    // todo: Missing JournalEntry

    public int getNdbNo() {
        return ndbNo;
    }

    public void setNdbNo(int NDB_No) {
        this.ndbNo = NDB_No;
    }

    public int getFoodGrpCode() {
        return foodGrpCode;
    }

    public void setFoodGrpCode(int fdGrpCd) {
        this.foodGrpCode = fdGrpCd;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public List<NutrientData> getNutDataList() {
        return nutDataList;
    }

    public void setNutDataList(List<NutrientData> nutDataList) {
        this.nutDataList = nutDataList;
    }

    public List<Weight> getWeights() { return weights; }

    public void setWeights(List<Weight> weights) { this.weights = weights; }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }
}
