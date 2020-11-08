package com.orders.demo.dataaccess;

import com.orders.demo.dao.HibernateDAOFactory;
import com.orders.demo.dto.OrderDTO;
import com.orders.demo.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author IsuruP
 */
@Component
public class OrderDataAccess {
    @Autowired
    private HibernateDAOFactory hibernateDaoFactory;

    public int addOrderData(OrderDTO orderDTO) {
        return this.hibernateDaoFactory.getOrderDAO().addOrderData(convertToOrderData(orderDTO));
    }

    public List<Order> getAllOrdersData() {
        return this.hibernateDaoFactory.getOrderDAO().getAllOrdersData();
    }

    public Order getOrderData(long id) {
        return this.hibernateDaoFactory.getOrderDAO().getOrderData(id);
    }

    private Order convertToOrderData(OrderDTO itemDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Order order = new Order();
        order.setAddress(itemDTO.getAddress());
        order.setItemId(itemDTO.getItemId());
        order.setQuantity(itemDTO.getQuantity());
        order.setOrderStatus(itemDTO.getStatus());
        order.setTotalPrize(itemDTO.getTotalPrize());
        order.setUserId(itemDTO.getUserId());
        return order;
    }
}
