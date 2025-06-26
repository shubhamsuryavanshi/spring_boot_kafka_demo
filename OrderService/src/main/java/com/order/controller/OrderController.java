package com.order.controller;

import com.order.dto.OrderDto;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkInventory")
    public ResponseEntity<Boolean> checkInventory(Long productId) {
        return new ResponseEntity<>(orderService.checkForInventory(productId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto order) {
        order = orderService.placeOrder(order);
        return new ResponseEntity<OrderDto>(order,HttpStatus.OK);
    }

    @PutMapping("/udpate")
    public ResponseEntity<OrderDto> udpateOrder(@RequestBody OrderDto order) {
        order = orderService.updateOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}