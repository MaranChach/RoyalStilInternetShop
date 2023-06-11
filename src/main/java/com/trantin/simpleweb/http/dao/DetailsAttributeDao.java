package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.DetailsAttribute;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class DetailsAttributeDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<DetailsAttribute> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from DetailsAttribute").getResultList();
    }


    @Transactional
    public DetailsAttribute getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from DetailsAttribute where id = " + id, DetailsAttribute.class).getSingleResult();
    }


    @Transactional
    public void save(DetailsAttribute entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }
}
