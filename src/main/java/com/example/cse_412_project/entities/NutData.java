package com.example.cse_412_project.entities;


import javax.persistence.*;

@Entity
@Table(name = "NUT_DATA")
public class NutData {
    @EmbeddedId
    private NutDataKey nutDataKey;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "NDB_No", updatable = false)
    @MapsId("ndbNo")
    private FoodDescription foodDescription;

    // todo: complete the table with all the columns and the relationship + getter and setter
}
