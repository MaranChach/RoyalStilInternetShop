package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Address;
import com.trantin.simpleweb.http.entity.City;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CityDao {

    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public List<City> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from City", City.class).getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<City> cities =
                sessionFactory.getCurrentSession().createQuery("from City ", City.class).getResultList();

        Map<Integer, String> citiesMap = new HashMap<>();

        for (int i = 0; i < cities.size(); i++) {
            citiesMap.put(cities.get(i).getId(), cities.get(i).getName());
        }

        return citiesMap;
    }

    @Transactional
    public City getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from City where id = " + id, City.class).getSingleResult();
    }

    @Transactional
    public void save(City entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from City where id = " + id);
    }
}
