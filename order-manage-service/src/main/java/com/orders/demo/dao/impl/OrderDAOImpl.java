package com.orders.demo.dao.impl;

import com.orders.demo.dao.OrderDAO;
import com.orders.demo.models.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Console;
import java.util.List;

/**
 * @author IsuruP
 */
public class OrderDAOImpl implements OrderDAO {


    private SessionFactory sessionFactory;

    @Override
    public int addOrderData(final Order order){
        Session session = null;
        Transaction transaction = null;
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            status = 1;
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return status;
    }
    @Override
    public Order getOrderData(final long id){
        Session session = null;
        Transaction transaction = null;
        Order order = new Order();
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            Query query = session.createQuery("SELECT u FROM Order u WHERE u.Id=:id");
            query.setParameter("id", id);
            order =  (Order) query.uniqueResult();
            status = 1;
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public Order changeOrderStatus(final String orderStatus){
        Session session = null;
        Transaction transaction = null;
        Order order = new Order();
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            Query query = session.createQuery("UPDATE Order u SET u.status ="+orderStatus +"WHERE u.Id=:id");
            order =  (Order) query.uniqueResult();
            status = 1;
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }


    @Override
    public List<Order> getAllOrdersData(){
        Session session = null;
        Transaction transaction = null;
        List orderDataList = null;
        try {
            session = this.sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> rootEntry = cq.from(Order.class);
            CriteriaQuery<Order> all = cq.select(rootEntry);
            TypedQuery<Order> query = session.createQuery(all);
            orderDataList =  query.getResultList();
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return orderDataList;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
