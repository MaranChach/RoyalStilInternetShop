package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.OrderCart;
import org.aspectj.weaver.ast.Or;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderCartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<OrderCart> getAll(){
        return sessionFactory.getCurrentSession().createQuery("from OrderCart", OrderCart.class).getResultList();
    }

    @Transactional
    public OrderCart getById(int id){
        return sessionFactory.getCurrentSession().createQuery("from OrderCart where id = " + id, OrderCart.class).getSingleResult();
    }

    @Transactional
    public OrderCart getBySessionId(String sessionId){
        return sessionFactory.getCurrentSession().createQuery("from OrderCart where uid LIKE '" + sessionId + "'", OrderCart.class).getSingleResult();
    }

    @Transactional
    public void saveOrderCart(OrderCart orderCart){
        sessionFactory.getCurrentSession().saveOrUpdate(orderCart);
    }

}
