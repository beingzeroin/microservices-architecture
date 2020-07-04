package com.aditya.catalog.repository;

import com.aditya.catalog.entity.CatalogBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogBrandRepository extends JpaRepository<CatalogBrand, Integer> {

}
