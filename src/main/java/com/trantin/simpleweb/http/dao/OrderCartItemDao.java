package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.OrderCartItem;
import com.trantin.simpleweb.http.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public class OrderCartItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<OrderCartItem> getAll(){
        return sessionFactory.getCurrentSession().createQuery("from OrderCartItem", OrderCartItem.class).getResultList();
    }

    @Transactional
    public OrderCartItem getById(int id){
        return sessionFactory.getCurrentSession().createQuery("from OrderCartItem where id = " + id, OrderCartItem.class).getSingleResult();
    }

    @Transactional
    public OrderCartItem getItemFromCart(int cartId, int productId){
        return sessionFactory.getCurrentSession().createQuery("from OrderCartItem where orderCart.id = " + cartId + " and product.id = " + productId, OrderCartItem.class).getSingleResult();
    }

    @Transactional
    public void saveOrderCart(OrderCartItem orderCartItem){
        sessionFactory.getCurrentSession().saveOrUpdate(orderCartItem);
    }


    @Transactional
    public void delete(OrderCartItem orderCartItem){
        sessionFactory.getCurrentSession().delete(orderCartItem);
    }
}
