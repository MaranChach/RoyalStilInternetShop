package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.Image;
import com.trantin.simpleweb.http.entity.Metadata;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MetadataDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Metadata> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Metadata", Metadata.class).getResultList();
    }

    @Transactional
    public Metadata getByKey(String key) {
        return sessionFactory.getCurrentSession().createQuery("FROM Metadata WHERE key LIKE '" + key + "'", Metadata.class).getSingleResult();
    }

    @Transactional
    public void save(Metadata entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(Metadata image) {
        sessionFactory.getCurrentSession().delete(image);
    }

    @Transactional
    public void deleteByKey(String key) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Metadata WHERE key LIKE " + key).executeUpdate();
    }

}
