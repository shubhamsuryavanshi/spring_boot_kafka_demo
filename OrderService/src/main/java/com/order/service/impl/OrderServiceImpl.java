package com.order.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.order.client.ApiClient;
import com.order.config.NotificationProducer;
import com.order.config.OrderProducer;
import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.event.EmailEvent;
import com.order.event.OrderEvent;
import com.order.model.Order;
import com.order.model.Product;
import com.order.model.User;
import com.order.repo.OrderRepository;
import com.order.repo.ProductRepository;
import com.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ApiClient client;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderProducer orderKafka;

    @Autowired
    private NotificationProducer notifyKafka;

    @Autowired
    private ProductRepository prodRepo;

    @Override
    public Boolean checkForInventory(Long productId) {
        return client.checkForInventory(productId);
    }

    @Override
    public OrderDto placeOrder(OrderDto orderdto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderdto,order);

        List<ProductDto> productDtoList = orderdto.getItems();
        List<Product> prodList = new ArrayList<>();

        for (int i = 0; i < productDtoList.size(); i++) {
            Product prod = new Product();
            BeanUtils.copyProperties(productDtoList.get(i),prod);
            prodList.add(prod);
        }
        prodList = prodRepo.saveAll(prodList);
        order.setItems(prodList);
        orderdto.setStatus("PENDING");

        order.setPlacedBy(1L);
        order = orderRepo.save(order);

        //send mail for order placed with pending status
        sendEmailNotification(order);
        sendOrerToInventoryNotification(order);

        return orderdto;
    }

    private void sendOrerToInventoryNotification(Order order) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(order.getId());
        orderEvent.setStatus(order.getStatus());
        orderEvent.setMessage("Order created successfully!!");
        orderKafka.sendMessage(orderEvent);
    }

    private void sendEmailNotification(Order order) {
        EmailEvent emailEvent = new EmailEvent();
        emailEvent.setEmail("test@mailinator.com");
        emailEvent.setStatus(order.getStatus());
        emailEvent.setOrder_id(order.getId());
        //emailEvent.setUser_id(order.getPlacedBy().getId());

        notifyKafka.sendNotification(emailEvent);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderdto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderdto,order);

        List<ProductDto> productDtoList = orderdto.getItems();
        List<Product> prodList = new ArrayList<>();

        for (int i = 0; i < productDtoList.size(); i++) {
            Product prod = new Product();
            BeanUtils.copyProperties(productDtoList.get(i),prod);
            prodList.add(prod);
        }
        order.setItems(prodList);

        orderdto.setStatus(orderdto.getStatus());

        //send mail based on order status

        orderRepo.save(order);
        return orderdto;
    }
}
