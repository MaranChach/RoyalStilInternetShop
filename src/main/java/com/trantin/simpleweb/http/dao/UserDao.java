package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Unit;
import com.trantin.simpleweb.http.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public List<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM User ", User.class).getResultList();
    }

    @Transactional
    public User getByUsername(String username) {
        return sessionFactory.getCurrentSession().createQuery("FROM User WHERE username LIKE '" + username + "'", User.class).getSingleResult();
    }

    @Transactional
    public void save(User entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void deleteByUsername(String username) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE username LIKE " + username).executeUpdate();
    }

    @Transactional
    public void delete(User entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
}
