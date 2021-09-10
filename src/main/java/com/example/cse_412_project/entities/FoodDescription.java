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
    private int fdGrpCd;

    @Column(name = "Long_Desc")
    private String longDesc;

    @Column(name = "Shrt_Desc")
    private String shortDesc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodDescription", cascade = CascadeType.DETACH)
    private List<NutData> nutDataList;

    // todo: Missing Weight

    // todo: Missing fdGroup

    // todo: Missing JournalEntry

    public int getNdbNo() {
        return ndbNo;
    }

    public void setNdbNo(int NDB_No) {
        this.ndbNo = NDB_No;
    }

    public int getFdGrpCd() {
        return fdGrpCd;
    }

    public void setFdGrpCd(int fdGrpCd) {
        this.fdGrpCd = fdGrpCd;
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

    public List<NutData> getNutDataList() {
        return nutDataList;
    }

    public void setNutDataList(List<NutData> nutDataList) {
        this.nutDataList = nutDataList;
    }
}
