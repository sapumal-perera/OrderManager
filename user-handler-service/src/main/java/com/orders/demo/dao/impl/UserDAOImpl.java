package com.orders.demo.dao.impl;

import com.orders.demo.dao.UserDAO;
import com.orders.demo.models.SysUser;
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
public class UserDAOImpl implements UserDAO
{
    private SessionFactory sessionFactory;
    public int addUserData(final SysUser sysUser){
        Session session = null;
        Transaction transaction = null;
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(sysUser);
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

    public SysUser getUserDetails(final String userName){
        Session session = null;
        Transaction transaction = null;
        SysUser sysUser = new SysUser();
        int status = 0;
        try {
            session = this.sessionFactory.openSession();
            Query query = session.createQuery("SELECT u FROM SysUser u WHERE u.userName=:userName");
            query.setParameter("userName", userName);
            sysUser =  (SysUser) query.uniqueResult();
            status = 1;
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return sysUser;
    }


    public List<SysUser> getAllUserDetails(){
        Session session = null;
        Transaction transaction = null;
        List sysUserDataList = null;
        try {
            session = this.sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<SysUser> cq = cb.createQuery(SysUser.class);
            Root<SysUser> rootEntry = cq.from(SysUser.class);
            CriteriaQuery<SysUser> all = cq.select(rootEntry);
            TypedQuery<SysUser> query = session.createQuery(all);
            sysUserDataList =  query.getResultList();
        } catch (Exception e) {
            //
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return sysUserDataList;
    }



    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
