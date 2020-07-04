package com.aditya.catalog.repository;

import com.aditya.catalog.entity.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Integer> {

    CatalogItem findById(int id);

    Page<CatalogItem> findAllByIsDeleted(boolean isdeleted, Pageable pageable);
}
