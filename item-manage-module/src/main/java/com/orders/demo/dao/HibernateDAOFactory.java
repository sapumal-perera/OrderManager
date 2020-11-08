package com.orders.demo.dao;

import com.orders.demo.dao.impl.ItemDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

/**
 * @author IsuruP
 */
@Component
public class HibernateDAOFactory extends  DAOFactory {

    private SessionFactory hibernateFactory;

    @Autowired
    public HibernateDAOFactory(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    private SessionFactory session;
    private ItemDAOImpl itemDAO;
    public synchronized ItemDAOImpl getItemDAO() {
        if (itemDAO == null) {
            itemDAO = new ItemDAOImpl();
            itemDAO.setSessionFactory(this.hibernateFactory);
        }
        return itemDAO;
    }
}
