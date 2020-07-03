package com.aditya.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.catalog.entity.CatalogItem;
import com.aditya.catalog.repository.CatalogItemRepository;

@Service
public class CatalogItemService {

	@Autowired
	CatalogItemRepository itemRepo;

	public List<CatalogItem> getAllItems() {
		return itemRepo.findAll();
	}

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

	public void delete(int id) {
		itemRepo.deleteById(id);
	}

}
