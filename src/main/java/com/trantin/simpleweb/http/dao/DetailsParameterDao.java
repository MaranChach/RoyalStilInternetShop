package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.DetailsParameter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class DetailsParameterDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<DetailsParameter> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from DetailsParameter", DetailsParameter.class).getResultList();
    }


    @Transactional
    public DetailsParameter getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from DetailsParameter where id = " + id, DetailsParameter.class).getSingleResult();
    }


    @Transactional
    public void save(DetailsParameter entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    public void delete(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from DetailsParameter where id = " + id);
    }
}
