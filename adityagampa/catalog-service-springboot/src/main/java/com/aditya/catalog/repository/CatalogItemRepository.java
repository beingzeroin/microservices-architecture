package com.aditya.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.catalog.entity.CatalogItem;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Integer> {

	CatalogItem findById(int id);
}
