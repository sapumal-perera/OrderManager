package com.orders.demo.dao;

import com.orders.demo.model.Item;

/**
 * @author IsuruP
 */
public interface ItemDAO {
    int addItemData(final com.orders.demo.model.Item item);
    Item getItemData(int id);
}
