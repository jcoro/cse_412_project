package com.example.cse_412_project.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class WeightKey implements Serializable {

    public WeightKey() {}

    public WeightKey(final int ndbNo, final int seq) {
        this.setNdbNo(ndbNo);
        this.setSeq(seq);
    }

    @Column(name = "NDB_No", nullable = false)
    private int ndbNo;

    @Column(name = "Seq", nullable = false)
    private int seq;

    public int getNdbNo() {
        return ndbNo;
    }

    public void setNdbNo(int ndbNo) {
        this.ndbNo = ndbNo;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ndbNo;
        hash = hash * prime + this.seq;
        return hash;
    }
}
