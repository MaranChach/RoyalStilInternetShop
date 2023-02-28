package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductDao {
    @Autowired
    private SessionFactory sessionFactory;



    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Product").getResultList();
    }


    public Map<String, Integer> getMap() {

        List<Product> products =
                sessionFactory.getCurrentSession().createQuery("from Product").getResultList();

        Map<String, Integer> productMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            productMap.put(products.get(i).getName(), products.get(i).getId());
        }

        return productMap;
    }



    public Product getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Product where id = " + id, Product.class).getSingleResult();
    }


    public List<Product> getByManufacturer(Manufacturer manufacturer){
        return sessionFactory.getCurrentSession().createQuery("from Product where manufacturer = " + manufacturer, Product.class).getResultList();
    }


    public List<Product> getByCategory(Category category){
        return sessionFactory.getCurrentSession().createQuery("from where category = " + category, Product.class).getResultList();
    }


    public List<Product> getByDetail(DetailsParameter parameter, double value){
        List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product").getResultList();

        List<Product> result = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            List<DetailsAttribute> attributes = product.getDetails().getAttributes();

            for (int j = 0; j < attributes.size(); j++) {
                DetailsAttribute curAttr = attributes.get(j);

                if (curAttr.getParameter().equals(parameter.getName()) && (curAttr.getValue() == value))
                    result.add(product);
            }
        }

        return result;
    }


    public void save(Product entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }


    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(id);
    }
}
