package com.orders.demo.dao.impl;
import com.orders.demo.dao.ItemDAO;
import com.orders.demo.model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author IsuruP
 */

public class ItemDAOImpl implements ItemDAO
{
    private SessionFactory sessionFactory;
    public int addItemData(final Item item){
        Session session = null;
        Transaction transaction = null;
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            status = 1;
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return status;
    }

    @Override
    public Item getItemData(int id) {
        Session session = null;
        Transaction transaction = null;
        Item item = new Item();
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            Query query = session.createQuery("SELECT u FROM Item u WHERE u.Id=:id");
            query.setParameter("id", id);
            item =  (Item) query.uniqueResult();
            status = 1;
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    public List<Item> getAllItemData(){
        Session session = null;
        Transaction transaction = null;
        List ItemDataList = null;
        try {
            session = this.sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Item> cq = cb.createQuery(Item.class);
            Root<Item> rootEntry = cq.from(Item.class);
            CriteriaQuery<Item> all = cq.select(rootEntry);
            TypedQuery<Item> query = session.createQuery(all);
            ItemDataList =  query.getResultList();
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ItemDataList;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
