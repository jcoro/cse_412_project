package com.example.cse_412_project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodDescriptionDto {

    @JsonProperty("ndbNo")
    private int ndbNo;

    @JsonProperty("fdGrpCd")
    private String fdGrpCd;

    @JsonProperty("longDesc")
    private String longDesc;

    @JsonProperty("shortDesc")
    private String shortDesc;

    // todo: When someone create NutDataDto, uncomment this and its getter and setter.
//    @JsonProperty("nutDataList")
//    private List<NutDataDto> nutDataList;

    public int getNdbNo() {
        return ndbNo;
    }

    public void setNdbNo(int NDB_No) {
        this.ndbNo = NDB_No;
    }

    public String getFdGrpCd() {
        return fdGrpCd;
    }

    public void setFdGrpCd(String fdGrpCd) {
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

//    public List<NutData> getNutDataList() {
//        return nutDataList;
//    }
//
//    public void setNutDataList(List<NutData> nutDataList) {
//        this.nutDataList = nutDataList;
//    }
}
