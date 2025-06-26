package com.stock.service;

import com.stock.dto.ProductDto;

public interface InventoryService {

    Boolean checkForStock(Long productId);

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);
}
