package com.order.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiClient {

    @Autowired
    private WebClient webClient;

    public Boolean checkForInventory(Long prodId) {
        Boolean isAvailable = webClient.get().uri("").retrieve().bodyToMono(Boolean.class).block();
        return  isAvailable;
    }
}
