package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Category> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Category").getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<Category> products =
                sessionFactory.getCurrentSession().createQuery("from Category ").getResultList();

        Map<Integer, String> productMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            productMap.put(products.get(i).getId(), products.get(i).getName());
        }

        return productMap;
    }


    @Transactional
    public Category getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Category where id = " + id, Category.class).getSingleResult();
    }


    @Transactional
    public void save(Category entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    @Transactional
    public void delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
    }

    @Transactional
    public void deleteById(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Category where id = " + id).executeUpdate();
    }

}
