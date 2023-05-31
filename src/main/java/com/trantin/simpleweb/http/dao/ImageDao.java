package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Details;
import com.trantin.simpleweb.http.entity.Image;
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
public class ImageDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Image> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Image", Image.class).getResultList();
    }

    @Transactional
    public Image getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Image where id = " + id, Image.class).getSingleResult();
    }


    @Transactional
    public void save(Image entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(Image image) {
        sessionFactory.getCurrentSession().delete(image);
    }

    @Transactional
    public void deleteById(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Image where id = " + id).executeUpdate();
    }
}
