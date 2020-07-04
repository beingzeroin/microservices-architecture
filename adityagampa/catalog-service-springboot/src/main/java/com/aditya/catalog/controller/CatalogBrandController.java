package com.aditya.catalog.controller;

import com.aditya.catalog.dto.PaginatedResponse;
import com.aditya.catalog.entity.CatalogBrand;
import com.aditya.catalog.service.CatalogBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog/brands")
public class CatalogBrandController {

    @Autowired
    CatalogBrandService catalogBrandService;

    @GetMapping
    public PaginatedResponse<CatalogBrand> getCatalogBrands(@RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                            @RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) {
        return catalogBrandService.getCatalogBrands(pageIndex, pageSize);
    }

}
