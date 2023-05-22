package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.Authority;
import com.trantin.simpleweb.http.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthorityDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public List<Authority> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Authority ", Authority.class).getResultList();
    }

    @Transactional
    public Authority getByUsername(String username) {
        return sessionFactory.getCurrentSession().createQuery("FROM Authority WHERE username LIKE " + username, Authority.class).getSingleResult();
    }

    @Transactional
    public void save(Authority entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    @Transactional
    public void delete(Authority entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

}
