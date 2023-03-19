package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Address;
import com.trantin.simpleweb.http.entity.City;
import com.trantin.simpleweb.http.entity.Unit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AddressDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public List<Address> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Address", Address.class).getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<Address> addresses =
                sessionFactory.getCurrentSession().createQuery("from Address ", Address.class).getResultList();

        Map<Integer, String> addressesMap = new HashMap<>();

        for (int i = 0; i < addresses.size(); i++) {
            addressesMap.put(addresses.get(i).getId(), addresses.get(i).getSummary());
        }

        return addressesMap;
    }

    @Transactional
    public Address getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Address where id = " + id, Address.class).getSingleResult();
    }

    @Transactional
    public void save(Address entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Address where id = " + id);
    }
}

