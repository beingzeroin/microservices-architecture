package com.aditya.catalog.service;

import com.aditya.catalog.dto.PaginatedResponse;
import com.aditya.catalog.entity.CatalogItem;
import com.aditya.catalog.repository.CatalogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CatalogItemService {

    @Autowired
    CatalogItemRepository itemRepo;

    public int add(CatalogItem item) {
        CatalogItem savedItem = itemRepo.save(item);
        return savedItem.getId();
    }

    public CatalogItem getItem(int id) {
        return itemRepo.findById(id);
    }

    public int update(CatalogItem item) {
        return add(item);
    }

    @Transactional
    public void delete(int id) {
        CatalogItem item = itemRepo.findById(id);
        item.setIsDeleted(true);
        itemRepo.save(item);
    }

    public List<CatalogItem> getPaginatedItems(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<CatalogItem> results = itemRepo.findAllByIsDeleted(false,pageable);
        if (results.hasContent()) {
            return results.getContent();
        }
        return Collections.emptyList();
    }

    public PaginatedResponse<CatalogItem> getCatalogItems(Integer pageIndex, Integer pageSize) {

        List<CatalogItem> items = getPaginatedItems(pageIndex, pageSize);
        PaginatedResponse<CatalogItem> response = new PaginatedResponse<>();
        response.setItems(items);
        response.setPageIndex(pageIndex);
        response.setPageSize(pageSize);
        response.setTotalItems(getAllCountBrands());
        return response;
    }

    private long getAllCountBrands() {
        return itemRepo.count();
    }


}
