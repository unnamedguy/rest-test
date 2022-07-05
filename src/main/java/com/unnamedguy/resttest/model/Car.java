package com.unnamedguy.resttest.model;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "modelName")
    private String modelName;
    @Column(name = "color")
    private String color;
    @Column(name = "places")
    private Long places;

    public Car() {
    }

    public Car(String brand, String modelName, String color, Long places) {
        this.brand = brand;
        this.modelName = modelName;
        this.color = color;
        this.places = places;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getPlaces() {
        return places;
    }

    public void setPlaces(Long places) {
        this.places = places;
    }
}
