package com.example.cse_412_project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodDescriptionDto {

    @JsonProperty("ndbNo")
    private int ndbNo;

    @JsonProperty("fdGrpCd")
    private int fdGrpCd;

    @JsonProperty("longDesc")
    private String longDesc;

    @JsonProperty("shortDesc")
    private String shortDesc;

    public FoodDescriptionDto(int ndbNo, int fdGrpCd, String longDesc, String shortDesc){
        this.ndbNo = ndbNo;
        this.fdGrpCd = fdGrpCd;
        this.longDesc = longDesc;
        this.shortDesc = shortDesc;
    }

    // todo: When someone create NutDataDto, uncomment this and its getter and setter.
//    @JsonProperty("nutDataList")
//    private List<NutDataDto> nutDataList;

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

//    public List<NutData> getNutDataList() {
//        return nutDataList;
//    }
//
//    public void setNutDataList(List<NutData> nutDataList) {
//        this.nutDataList = nutDataList;
//    }
}
