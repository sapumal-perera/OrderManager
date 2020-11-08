package com.orders.demo.dao;

import com.orders.demo.models.Order;

import java.util.List;

/**
 * @author IsuruP
 */
public interface OrderDAO {
    int addOrderData(final Order order);
    Order getOrderData(final long id);
    List<Order> getAllOrdersData();
    Order changeOrderStatus(final String status);
}
