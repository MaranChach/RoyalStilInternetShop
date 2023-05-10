package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Client;
import com.trantin.simpleweb.http.entity.Order;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Order> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Order order by id desc ", Order.class).getResultList();
    }

    @Transactional
    public int getNewOrdersNum(){
        return sessionFactory.getCurrentSession().createQuery("SELECT id FROM Order WHERE confirmed = false").getResultList().size();
    }

    @Transactional
    public List<Order> getAllSortedByDate() {
        return sessionFactory.getCurrentSession().createQuery("from Order order by orderDate desc", Order.class).getResultList();
    }

    @Transactional
    public List<Object[]> getByLastWeek(){
        LocalDate dateFrom = LocalDate.now().minusDays(7);

        Query<java.util.Date> query = sessionFactory.getCurrentSession().createQuery("select orderDate from Order where orderDate >= '" + Date.valueOf(dateFrom).toString() + "'", java.util.Date.class);

        List<java.util.Date> orders = query.getResultList();

        HashMap<String, Integer> ordersNum = new HashMap<>();

        for (java.util.Date elem : orders) {
            System.out.println(elem);
            if (!ordersNum.containsKey(elem.toString())){

                ordersNum.put(elem.toString(), 1);
            }
            else{
                ordersNum.replace(elem.toString(), ordersNum.get(elem.toString()) + 1);
            }
        }
        List<Object[]> strings = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate curDate = LocalDate.now().minusDays(i);

            strings.add(new Object[]{String.valueOf(curDate.getDayOfMonth()) + "." + String.valueOf(curDate.getMonthValue()), ordersNum.getOrDefault(curDate.toString(), 0)});
        }

        System.out.println(strings);


        return strings;
    }


    @Transactional
    public Order getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Order where id = " + id, Order.class).getSingleResult();
    }

    @Transactional
    public Order getByUid(String uid) {
        return sessionFactory.getCurrentSession().createQuery("from Order where uid = '" + uid + "'", Order.class).getSingleResult();
    }

    @Transactional
    public List<Order> getByClient(Client client){
        return sessionFactory.getCurrentSession().createQuery("from Order where client = " + client, Order.class).getResultList();
    }

    @Transactional
    public List<Order> getByDate(Date date){
        return sessionFactory.getCurrentSession().createQuery("from Order where orderDate = '" + date + "'", Order.class).getResultList();
    }

    @Transactional
    public List<Order> getByDate(Date date, boolean newer){
        Query query = null;

        if (newer)
            query = sessionFactory.getCurrentSession().createQuery("from Order where orderDate > '" + date + "'");
        else
            query = sessionFactory.getCurrentSession().createQuery("from Order where orderDate < '" + date + "'");

        return query.getResultList();
    }

    @Transactional
    public List<Order> getYesterday(){
        return sessionFactory.getCurrentSession().createQuery("from Order where orderDate = '" + Date.valueOf(LocalDate.now().minusDays(1)) + "'", Order.class).getResultList();
    }

    @Transactional
    public List<Order> getByDateBetween(Date start, Date end){
        return sessionFactory.getCurrentSession().createQuery("from Order where orderDate < '" + start + "' and orderDate > '" + end + "'", Order.class).getResultList();
    }

    @Transactional
    public void saveOrUpdate(Order entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void persist(Order entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Transactional
    public void delete(Order order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    @Transactional
    public void deleteById(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Order where id = " + id);
    }
}