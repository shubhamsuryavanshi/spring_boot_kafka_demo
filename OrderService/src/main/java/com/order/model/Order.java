package com.order.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/*@Data
@AllArgsConstructor
@NoArgsConstructor*/
@Entity
@Table(name = "order_table")
public class Order extends BaseClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private Long placedBy;
    private Float total;
    private Float discount;
    private String status;

    @ManyToMany
    @JoinTable(
            name = "prod_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "id")    )
    private List<Product> items;

    public Order() { }

    public Long getId() {
        return order_id;
    }

    public void setId(Long order_id) {
        this.order_id = order_id;
    }

    public Long getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(Long placedBy) {
        this.placedBy = placedBy;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}