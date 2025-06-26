package com.order.dto;

import com.order.model.BaseClass;

import java.util.List;

public class OrderDto extends BaseClass {

    private Long id;
    //private UserDto placedBy;
    private float total;
    private float discount;
    private List<ProductDto> items;
    private String status;

    public OrderDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public UserDto getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(UserDto placedBy) {
        this.placedBy = placedBy;
    }*/

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public List<ProductDto> getItems() {
        return items;
    }

    public void setItems(List<ProductDto> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}