package com.aditya.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.catalog.entity.CatalogBrand;
import com.aditya.catalog.repository.CatalogBrandRepository;

@Service
public class CatalogBrandService {

	@Autowired
	private CatalogBrandRepository catalogBrandRepo;

	public List<CatalogBrand> getCatalogBrands() {
		return catalogBrandRepo.findAll();
	}

}
