package com.example.cse_412_project.DTO;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.JournalEntry;
import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.entities.Weight;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JournalEntryResponse {

    private final FoodDescriptionRepository foodDescriptionRepository;

    @JsonProperty("j_id")
    private int jId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("amount")
    private float amount;

    @JsonProperty("journal_date")
    private LocalDateTime journalDate;

    @JsonProperty("order_index")
    private int orderIndex;

    @JsonProperty("ndb_no")
    private int ndbNo;

    @JsonProperty("seq")
    private int seq;

    @JsonProperty("fdgrp_cd")
    private int fdgrpCd;

    @JsonProperty("long_desc")
    private String longDesc;

    @JsonProperty("measurement")
    private List<WeightDto> weightDtoList;

    @JsonProperty("nutrient_value")
    private List<NutrientDataDto> nutrientDataDtoList;

    public JournalEntryResponse(FoodDescriptionRepository foodDescriptionRepository, JournalEntry journalEntry) {
        this.foodDescriptionRepository = foodDescriptionRepository;
        this.jId = journalEntry.getjId();
        this.username = journalEntry.getUsername();
        this.amount = journalEntry.getAmount();
        this.journalDate = journalEntry.getJournalDate();
        this.orderIndex = journalEntry.getOrderIndex();
        this.ndbNo = journalEntry.getNdbNo();
        this.seq = journalEntry.getSeq();
        this.fdgrpCd = fetchFdgrpCd(journalEntry);
        this.longDesc = fetchLongDesc(journalEntry);
        this.weightDtoList = fetchWeights(journalEntry);
        this.nutrientDataDtoList = fetchNutrientData(journalEntry);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(LocalDateTime journalDate) {
        this.journalDate = journalDate;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

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

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public int getFdgrpCd() {
        return fdgrpCd;
    }

    public void setFdgrpCd(int fdgrpCd) {
        this.fdgrpCd = fdgrpCd;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public List<WeightDto> getWeightDtoList() {
        return weightDtoList;
    }

    public void setWeightDtoList(ArrayList<WeightDto> weightDtoArrayList) {
        this.weightDtoList = weightDtoArrayList;
    }

    public List<NutrientDataDto> getNutrientDataDtoList() {
        return nutrientDataDtoList;
    }

    public void setNutrientDataDtoList(ArrayList<NutrientDataDto> nutrientDataDtoArrayList) {
        this.nutrientDataDtoList = nutrientDataDtoArrayList;
    }

    private int fetchFdgrpCd(JournalEntry journalEntry) {
        int foodGroupCode = 0;
        Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(journalEntry.getNdbNo());
        if (foodDescription.isPresent()) {
            foodGroupCode = foodDescription.get().getFoodGrpCode();
        }
        return foodGroupCode;
    }

    private String fetchLongDesc(JournalEntry journalEntry) {
        String longDesc = "";
        Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(journalEntry.getNdbNo());
        if (foodDescription.isPresent()) {
            longDesc = foodDescription.get().getLongDesc();
        }
        return longDesc;
    }

    private List<WeightDto> fetchWeights(JournalEntry journalEntry){
        List<Weight> weights;
        List<WeightDto> weightDtos = new ArrayList<>();
        Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(journalEntry.getNdbNo());
        if (foodDescription.isPresent()) {
            weights = foodDescription.get().getWeights();
            weights.forEach(w -> weightDtos.add(new WeightDto(w.getWeightKey().getSeq(),
                    w.getGramWeight(), w.getDescription())));
        }
        return weightDtos;
    }

    private List<NutrientDataDto> fetchNutrientData(JournalEntry journalEntry){
        List<NutrientData> nutrientDataList;
        List<NutrientDataDto> nutrientDataDtoList = new ArrayList<>();
        Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(journalEntry.getNdbNo());
        if (foodDescription.isPresent()) {
            nutrientDataList = foodDescription.get().getNutDataList();
            nutrientDataList.forEach(nd -> nutrientDataDtoList.add(
                    new NutrientDataDto(nd.getNutDataKey().getNutrNo(), nd.getNutrVal())));
        }
        return nutrientDataDtoList;
    }
}
