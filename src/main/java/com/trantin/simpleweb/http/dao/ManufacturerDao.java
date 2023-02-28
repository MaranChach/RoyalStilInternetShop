package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Details;
import com.trantin.simpleweb.http.entity.Manufacturer;
import com.trantin.simpleweb.http.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ManufacturerDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Manufacturer> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Manufacturer").getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<Manufacturer> products =
                sessionFactory.getCurrentSession().createQuery("from Manufacturer").getResultList();

        Map<Integer, String> productMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            productMap.put(products.get(i).getId(), products.get(i).getName());
        }

        return productMap;
    }

    @Transactional
    public Manufacturer getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Manufacturer where id = " + id, Manufacturer.class).getSingleResult();
    }


    @Transactional
    public void save(Manufacturer entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(id);
    }

}
