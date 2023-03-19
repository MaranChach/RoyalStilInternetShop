package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Street;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StreetDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public List<Street> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Street", Street.class).getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<Street> streets =
                sessionFactory.getCurrentSession().createQuery("from Street ", Street.class).getResultList();

        Map<Integer, String> addressesMap = new HashMap<>();

        for (int i = 0; i < streets.size(); i++) {
            addressesMap.put(streets.get(i).getId(), streets.get(i).getName());
        }

        return addressesMap;
    }

    @Transactional
    public Street getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Street where id = " + id, Street.class).getSingleResult();
    }

    @Transactional
    public void save(Street entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Street where id = " + id);
    }

}
