package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.Product;
import com.trantin.simpleweb.http.entity.Unit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UnitDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public List<Unit> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Unit", Unit.class).getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<Unit> products =
                sessionFactory.getCurrentSession().createQuery("from Unit").getResultList();

        Map<Integer, String> productMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            productMap.put(products.get(i).getId(), products.get(i).getName());
        }

        return productMap;
    }

    @Transactional
    public Unit getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Unit where id = " + id, Unit.class).getSingleResult();
    }

    @Transactional
    public void save(Unit entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Unit where id = " + id);
    }

    @Transactional
    public void deleteunit(Unit unit) {
        sessionFactory.getCurrentSession().delete(unit);
    }
}
