package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.DetailsParameter;
import com.trantin.simpleweb.http.entity.Unit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class DetailsParameterDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<DetailsParameter> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from DetailsParameter", DetailsParameter.class).getResultList();
    }

    @Transactional
    public Map<Integer, String> getMap() {

        List<DetailsParameter> parameters =
                sessionFactory.getCurrentSession().createQuery("from DetailsParameter", DetailsParameter.class).getResultList();

        Map<Integer, String> parametersMap = new HashMap<>();

        for (int i = 0; i < parameters.size(); i++) {
            parametersMap.put(parameters.get(i).getId(), parameters.get(i).getName());
        }

        return parametersMap;
    }


    @Transactional
    public DetailsParameter getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from DetailsParameter where id = " + id, DetailsParameter.class).getSingleResult();
    }


    @Transactional
    public void save(DetailsParameter entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    @Transactional
    public void delete(DetailsParameter detailsParameter) {
        sessionFactory.getCurrentSession().delete(detailsParameter);
    }
}
