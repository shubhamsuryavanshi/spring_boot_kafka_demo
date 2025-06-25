package com.order.controller;

import com.order.dto.OrderDto;
import com.order.dto.OrderEvent;
import com.order.model.Order;
import com.order.service.OrderService;
import com.order.service.impl.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderProducer orderService;

    @PostMapping("/create")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setMessage("Order status is pending!!");
        orderEvent.setStatus("PENDING");

        orderService.sendMessage(orderEvent);

        return "Order placed successfully!!";
    }
}