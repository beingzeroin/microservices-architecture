package com.aditya.catalog.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@Table(name = "CATALOGITEM")
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CatalogItem {

    @Id
    int id;
    String name;
    String description;
    double price;
    String pictureFileName;
    String pictureUri;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogType_id")
    CatalogType catalogType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogBrand_id")
    CatalogBrand catalogBrand;
    int availableStock;
    int restockThreshold;
    int maxstockThreshold;
    @Column(name = "IS_DELETED")
    boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public CatalogType getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(CatalogType catalogType) {
        this.catalogType = catalogType;
    }

    public CatalogBrand getCatalogBrand() {
        return catalogBrand;
    }

    public void setCatalogBrand(CatalogBrand catalogBrand) {
        this.catalogBrand = catalogBrand;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public int getRestockThreshold() {
        return restockThreshold;
    }

    public void setRestockThreshold(int restockThreshold) {
        this.restockThreshold = restockThreshold;
    }

    public int getMaxstockThreshold() {
        return maxstockThreshold;
    }

    public void setMaxstockThreshold(int maxstockThreshold) {
        this.maxstockThreshold = maxstockThreshold;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CatalogItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
                + ", pictureFileName=" + pictureFileName + ", pictureUri=" + pictureUri + ", catalogType=" + catalogType
                + ", catalogBrand=" + catalogBrand + ", availableStock=" + availableStock + ", restockThreshold="
                + restockThreshold + ", maxstockThreshold=" + maxstockThreshold + ", isDeleted=" + isDeleted + "]";
    }

}
