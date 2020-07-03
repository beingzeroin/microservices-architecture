package com.aditya.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.catalog.entity.CatalogType;
import com.aditya.catalog.service.CatalogTypeService;

@RestController
public class CatalogTypeController {

	@Autowired
	CatalogTypeService catalogTypeService;

	@GetMapping("/api/v1/catalog/types")
	public List<CatalogType> getCatalogTypes() {
		List<CatalogType> catalogTypes = catalogTypeService.getCatalogTypes();
		return catalogTypes;
	}

}
