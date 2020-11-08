package com.orders.demo.models;

import javax.persistence.*;

/**
 * @author IsuruP
 */
@Entity
@Table(name = "item_order")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Order {

    @Id
    @GeneratedValue
    private long Id;
    @Column(name="total_prize")
    private long totalPrize;
    @Column(name="item_id")
    private int itemId;
    @Column(name="quantity")
    private int quantity;
    @Column(name="address")
    private String address;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="user_id")
    private long userId;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(long totalPrize) {
        this.totalPrize = totalPrize;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
