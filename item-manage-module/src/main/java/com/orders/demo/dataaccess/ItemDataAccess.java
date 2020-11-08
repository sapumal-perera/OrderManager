package com.orders.demo.dataaccess;

import com.orders.demo.dao.HibernateDAOFactory;
import com.orders.demo.dto.ItemDTO;
import com.orders.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author IsuruP
 */

@Component
public class ItemDataAccess {
    @Autowired
    private HibernateDAOFactory hibernateDaoFactory;

    public int addItemData(ItemDTO contentDTO) {
        return this.hibernateDaoFactory.getItemDAO().addItemData(convertToContentData(contentDTO));
    }

    public List<Item> getAllItemData() {
        return this.hibernateDaoFactory.getItemDAO().getAllItemData();
    }

    public Item getItemData(int id) {
        return this.hibernateDaoFactory.getItemDAO().getItemData(id);
    }

    private Item convertToContentData(ItemDTO itemDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Item order = new Item();
        order.setItemDescription(itemDTO.getItemDescription());
        order.setItemName(itemDTO.getItemName());
        order.setPrice(itemDTO.getPrice());
        return order;
    }
}
