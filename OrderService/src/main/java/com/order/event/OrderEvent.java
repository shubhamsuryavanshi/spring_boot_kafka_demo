package com.order.event;

import lombok.Data;
import java.io.Serializable;

public class OrderEvent implements Serializable {
    String message;
    String status;
    Long orderId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
