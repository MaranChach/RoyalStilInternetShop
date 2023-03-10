package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Client;
import com.trantin.simpleweb.http.entity.Order;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Order> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Order").getResultList();
    }


    @Transactional
    public Order getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Order where id = " + id, Order.class).getSingleResult();
    }

    @Transactional
    public List<Order> getByClient(Client client){
        return sessionFactory.getCurrentSession().createQuery("from Order where client = " + client).getResultList();
    }

    @Transactional
    public List<Order> getByDate(Date date){
        return sessionFactory.getCurrentSession().createQuery("from Order where date = " + date).getResultList();
    }



    @Transactional
    public List<Order> getByDate(Date date, boolean newer){
        Query query = null;


        if (newer)
            query = sessionFactory.getCurrentSession().createQuery("from Order where date > " + date);
        else
            query = sessionFactory.getCurrentSession().createQuery("from Order where date < " + date);

        return query.getResultList();
    }

    @Transactional
    public void save(Order entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(id);
    }
}