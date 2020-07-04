package com.aditya.catalog.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATALOGBRAND")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CatalogBrand {

    @Id
    int id;
    String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CatalogBrand [id=" + id + ", brand=" + brand + "]";
    }
}
