package com.aditya.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.catalog.entity.CatalogBrand;
import com.aditya.catalog.service.CatalogBrandService;

@RestController
public class CatalogBrandController {

	@Autowired
	CatalogBrandService catalogBrandService;

	@GetMapping("/api/v1/catalog/brands")
	public List<CatalogBrand> getCatalogBrands() {
		List<CatalogBrand> catalogBrands = catalogBrandService.getCatalogBrands();
		return catalogBrands;
	}

}
