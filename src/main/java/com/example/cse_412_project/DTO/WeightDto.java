package com.example.cse_412_project.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeightDto {

    @JsonProperty("seq")
    private int seq;

    @JsonProperty("gramweight")
    private float gramweight;

    @JsonProperty("measdesc")
    private String measdesc;

    public WeightDto(int seq, float gramweight, String measdesc){
        this.seq = seq;
        this.gramweight = gramweight;
        this.measdesc = measdesc;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public float getGramweight() {
        return gramweight;
    }

    public void setGramweight(float gramweight) {
        this.gramweight = gramweight;
    }

    public String getMeasdesc() {
        return measdesc;
    }

    public void setMeasdesc(String measdesc) {
        this.measdesc = measdesc;
    }
}
