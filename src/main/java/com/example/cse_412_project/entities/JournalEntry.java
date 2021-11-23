package com.example.cse_412_project.entities;

import com.example.cse_412_project.entities.impl.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "JOURNAL_ENTRY")
public class JournalEntry {
    @Id
    @Column(name = "j_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jId;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name = "amount")
    private float amount;

    @Column(name = "journal_date")
    private LocalDateTime journalDate;

    @Column(name = "order_index")
    private int orderIndex;

    @Column(name = "NDB_No", nullable = false)
    private int ndbNo;

    @Column(name = "seq", nullable = false)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "username", referencedColumnName = "username", insertable=false, updatable=false)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "NDB_No", referencedColumnName = "NDB_No", insertable=false, updatable=false)
    private FoodDescription foodDescription;

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public FoodDescription getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(FoodDescription foodDescription) {
        this.foodDescription = foodDescription;
    }
}
