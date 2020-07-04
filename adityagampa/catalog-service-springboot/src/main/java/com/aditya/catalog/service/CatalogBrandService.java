package com.aditya.catalog.service;

import com.aditya.catalog.dto.PaginatedResponse;
import com.aditya.catalog.entity.CatalogBrand;
import com.aditya.catalog.repository.CatalogBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CatalogBrandService {

    @Autowired
    CatalogBrandRepository catalogBrandRepo;

    public List<CatalogBrand> getPaginatedBrands(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<CatalogBrand> results = catalogBrandRepo.findAll(pageable);
        if (results.hasContent()) {
            return results.getContent();
        }
        return Collections.emptyList();
    }

    public PaginatedResponse<CatalogBrand> getCatalogBrands(Integer pageIndex, Integer pageSize) {

        List<CatalogBrand> brands = getPaginatedBrands(pageIndex, pageSize);
        PaginatedResponse<CatalogBrand> response = new PaginatedResponse<>();
        response.setItems(brands);
        response.setPageIndex(pageIndex);
        response.setPageSize(pageSize);
        response.setTotalItems(getAllCountBrands());
        return response;
    }

    private long getAllCountBrands() {
        return catalogBrandRepo.count();
    }

}
