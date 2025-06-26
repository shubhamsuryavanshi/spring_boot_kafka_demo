package com.stock.controller;

import com.stock.dto.ProductDto;
import com.stock.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventoryCheck")
    public ResponseEntity<Boolean> inventoryCheck(@RequestParam Long productId) {
        return new ResponseEntity<>(inventoryService.checkForStock(productId), HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(inventoryService.saveProduct(productDto), HttpStatus.OK);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(inventoryService.updateProduct(productDto),HttpStatus.OK);
    }
}