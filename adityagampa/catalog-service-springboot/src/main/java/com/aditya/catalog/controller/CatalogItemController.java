package com.aditya.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.catalog.entity.CatalogItem;
import com.aditya.catalog.service.CatalogItemService;

@RestController
@RequestMapping("/api/v1/catalog/items")
public class CatalogItemController {

	@Autowired
	CatalogItemService itemSvc;

	@GetMapping
	public List<CatalogItem> getAllItems() {
		return itemSvc.getAllItems();
	}

	@GetMapping("/{id}")
	public CatalogItem getItem(@PathVariable("id") int id) {
		return itemSvc.getItem(id);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Integer> addItem(@RequestBody CatalogItem item) {
		int itemId = itemSvc.add(item);
		return new ResponseEntity<>(itemId, HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseBody
	public ResponseEntity<Integer> updateItem(@RequestBody CatalogItem item) {
		int itemId = itemSvc.update(item);
		return new ResponseEntity<>(itemId, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") int id) {
		itemSvc.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
