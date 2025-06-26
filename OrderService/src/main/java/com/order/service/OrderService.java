package com.order.service;

import com.order.dto.OrderDto;

public interface OrderService {
    Boolean checkForInventory(Long productId);

    OrderDto placeOrder(OrderDto order);

    OrderDto updateOrder(OrderDto order);
}
