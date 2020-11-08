package com.orders.demo.datamanager;

import com.orders.demo.dataaccess.OrderDataAccess;
import com.orders.demo.dto.OrderDTO;
import com.orders.demo.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author IsuruP
 */

@Component
public class OrderDataManager {
    @Autowired
    private OrderDataAccess orderDataAccess;


    public List<Order> getAllOrdersData() {
        return orderDataAccess.getAllOrdersData();
    }

    public Order getOrderData(long id) {
        return orderDataAccess.getOrderData(id);
    }

    public int addOrderData(OrderDTO orderDTO) {
        return orderDataAccess.addOrderData(orderDTO);
    }
}
