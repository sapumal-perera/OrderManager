package com.orders.demo.datamanager;

import com.orders.demo.dataaccess.ItemDataAccess;
import com.orders.demo.dto.ItemDTO;
import com.orders.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author IsuruP
 */
@Component
public class ItemDataManager {
    @Autowired
    private ItemDataAccess itemDataAccess;


    public int addItemData(ItemDTO item) {
        return itemDataAccess.addItemData(item);
    }

    public Item getItemData(int id) {
        return itemDataAccess.getItemData(id);
    }

    public List<Item> getAllItemData() {
        return this.itemDataAccess.getAllItemData();
    }
}
