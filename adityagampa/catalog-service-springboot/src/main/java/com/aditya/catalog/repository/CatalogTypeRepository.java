package com.aditya.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.catalog.entity.CatalogType;

@Repository
public interface CatalogTypeRepository extends JpaRepository<CatalogType, Integer> {

}
