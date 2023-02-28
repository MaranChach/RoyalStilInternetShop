package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Client;
import com.trantin.simpleweb.http.entity.Details;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClientDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Client> getByName(String name){
        return sessionFactory.getCurrentSession().createQuery("from Client where name = " + name).getResultList();
    }

    @Transactional
    public List<Client> getBySurname(String surname){
        return sessionFactory.getCurrentSession().createQuery("from Client where surname = " + surname).getResultList();
    }

    @Transactional
    public List<Client> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Client").getResultList();
    }


    @Transactional
    public Client getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Client where id = " + id, Client.class).getSingleResult();
    }


    @Transactional
    public void save(Client entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(id);
    }
}
