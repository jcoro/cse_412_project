package com.example.cse_412_project.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NutDataKey implements Serializable {
    @Column(name = "NDB_No", nullable = false)
    private int ndbNo;

    @Column(name = "Nutr_No", nullable = false)
    private int nutrNo;

    public int getNdbNo() {
        return ndbNo;
    }

    public void setNdbNo(int ndbNo) {
        this.ndbNo = ndbNo;
    }

    public int getNutrNo() {
        return nutrNo;
    }

    public void setNutrNo(int nutrNo) {
        this.nutrNo = nutrNo;
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ndbNo;
        hash = hash * prime + this.nutrNo;
        return hash;
    }
}
