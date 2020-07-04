package com.aditya.catalog.controller;

import com.aditya.catalog.dto.PaginatedResponse;
import com.aditya.catalog.entity.CatalogItem;
import com.aditya.catalog.service.CatalogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalog/items")
public class CatalogItemController {

    @Autowired
    CatalogItemService itemSvc;

    @GetMapping
    public PaginatedResponse<CatalogItem> getCatalogItems(@RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                          @RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) {
        return itemSvc.getCatalogItems(pageIndex, pageSize);
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
