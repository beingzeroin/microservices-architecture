package com.aditya.catalog.repository;

import com.aditya.catalog.entity.CatalogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogTypeRepository extends JpaRepository<CatalogType, Integer> {

}
