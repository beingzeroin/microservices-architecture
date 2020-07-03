package com.aditya.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.catalog.entity.CatalogBrand;

@Repository
public interface CatalogBrandRepository extends JpaRepository<CatalogBrand, Integer> {

}
