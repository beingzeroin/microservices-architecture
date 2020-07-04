package com.aditya.catalog.controller;

import com.aditya.catalog.dto.PaginatedResponse;
import com.aditya.catalog.entity.CatalogType;
import com.aditya.catalog.service.CatalogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogTypeController {

    @Autowired
    CatalogTypeService catalogTypeService;

    @GetMapping("/api/v1/catalog/types")
    public PaginatedResponse<CatalogType> getCatalogTypes(Integer pageIndex, Integer pageSize) {
        return catalogTypeService.getCatalogTypes(pageIndex, pageSize);
    }

}
