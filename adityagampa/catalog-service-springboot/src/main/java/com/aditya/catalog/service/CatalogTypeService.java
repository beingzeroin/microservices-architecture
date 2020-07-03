package com.aditya.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.catalog.entity.CatalogType;
import com.aditya.catalog.repository.CatalogTypeRepository;

@Service
public class CatalogTypeService {

	@Autowired
	private CatalogTypeRepository catalogTypeRepo;

	public List<CatalogType> getCatalogTypes() {
		return catalogTypeRepo.findAll();
	}

}
