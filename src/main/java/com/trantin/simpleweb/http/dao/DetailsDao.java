package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Details;
import com.trantin.simpleweb.http.entity.DetailsAttribute;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DetailsDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Details> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Details").getResultList();
    }


    @Transactional
    public Details getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Details where id = " + id, Details.class).getSingleResult();
    }


    @Transactional
    public void save(Details entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(id);
    }
}
