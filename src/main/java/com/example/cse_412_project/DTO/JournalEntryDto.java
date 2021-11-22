package com.example.cse_412_project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class JournalEntryDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("amount")
    private float amount;

    @JsonProperty("journalDate")
    private LocalDateTime journalDate;

    @JsonProperty("orderIndex")
    private int orderIndex;

    @JsonProperty("ndbNo")
    private int ndbNo;

    @JsonProperty("seq")
    private int seq;

    public JournalEntryDto(String username,
                           float amount,
                           LocalDateTime journalDate,
                           int orderIndex,
                           int ndbNo,
                           int seq) {
        this.username = username;
        this.amount = amount;
        this.journalDate = journalDate;
        this.orderIndex = orderIndex;
        this.ndbNo = ndbNo;
        this.seq = seq;
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
}
