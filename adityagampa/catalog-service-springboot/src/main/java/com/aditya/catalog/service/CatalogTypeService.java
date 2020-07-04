package com.aditya.catalog.service;

import com.aditya.catalog.dto.PaginatedResponse;
import com.aditya.catalog.entity.CatalogType;
import com.aditya.catalog.repository.CatalogTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CatalogTypeService {

    @Autowired
    CatalogTypeRepository catalogTypeRepo;

    public List<CatalogType> getPaginatedTypes(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<CatalogType> results = catalogTypeRepo.findAll(pageable);
        if (results.hasContent()) {
            return results.getContent();
        }
        return Collections.emptyList();
    }

    public PaginatedResponse<CatalogType> getCatalogTypes(Integer pageIndex, Integer pageSize) {

        List<CatalogType> types = getPaginatedTypes(pageIndex, pageSize);
        PaginatedResponse<CatalogType> response = new PaginatedResponse<>();
        response.setItems(types);
        response.setPageIndex(pageIndex);
        response.setPageSize(pageSize);
        response.setTotalItems(getAllCountBrands());
        return response;
    }

    private long getAllCountBrands() {
        return catalogTypeRepo.count();
    }

}
