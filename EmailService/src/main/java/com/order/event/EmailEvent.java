package com.order.event;

public class EmailEvent {
    Long order_id;
    Long user_id;
    String status;
    String email;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailEvent{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

