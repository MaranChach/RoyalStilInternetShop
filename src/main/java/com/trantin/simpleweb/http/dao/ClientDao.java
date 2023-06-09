package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Client;
import com.trantin.simpleweb.http.entity.Details;
import com.trantin.simpleweb.http.entity.Order;
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
        return sessionFactory.getCurrentSession().createQuery("from Client where name = " + name, Client.class).getResultList();
    }

    @Transactional
    public boolean isExistsByPhoneNumber(String phoneNumber){
        return sessionFactory.getCurrentSession().createQuery
                ("FROM Client WHERE phoneNumber LIKE '" + phoneNumber + "'").getResultList().size() != 0;
    }

    @Transactional
    public List<Client> getBySurname(String surname){
        return sessionFactory.getCurrentSession().createQuery("from Client where surname = " + surname, Client.class).getResultList();
    }

    @Transactional
    public List<Client> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Client", Client.class).getResultList();
    }


    @Transactional
    public Client getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Client where id = " + id, Client.class).getSingleResult();
    }

    @Transactional
    public List<Order> getOrders(Client client) {
        return sessionFactory.getCurrentSession().createQuery("from Order where client.id = " + client.getId(), Order.class).getResultList();
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
