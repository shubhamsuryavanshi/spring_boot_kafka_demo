package com.stock.service.impl;

import com.stock.dto.ProductDto;
import com.stock.model.Product;
import com.stock.repository.InventoryRepository;
import com.stock.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;

    @Override
    public Boolean checkForStock(Long productId) {

        Product prod = inventoryRepo.findById(productId).get();

        if (prod.getQuantity() > 1) {
            return  true;
        }
        return false;
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product prod = new Product();

        BeanUtils.copyProperties(productDto,prod);
        prod.setCreate_time(new Date());
        prod = inventoryRepo.save(prod);

        BeanUtils.copyProperties(prod,productDto);

        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product prod = inventoryRepo.findById(productDto.getId()).orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(productDto,prod);
        prod.setUpdate_time(new Date());
        prod = inventoryRepo.save(prod);

        BeanUtils.copyProperties(prod,productDto);
        return productDto;
    }
}