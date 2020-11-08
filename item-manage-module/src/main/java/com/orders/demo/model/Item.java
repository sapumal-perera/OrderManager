package com.orders.demo.model;

import javax.persistence.*;

/**
 * @author IsuruP
 */
@Entity
@Table(name = "item")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Item {

    @Id
    @GeneratedValue
    private int Id;
    @Column(name="item_name",unique = true)
    private String itemName;
    @Column(name="item_description",unique = true)
    private String itemDescription;
    @Column(name="price")
    private double price;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
