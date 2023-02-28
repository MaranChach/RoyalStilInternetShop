package com.trantin.simpleweb.http.services;


import com.trantin.simpleweb.http.dao.Dao;
import com.trantin.simpleweb.http.dao.UnitDao;
import com.trantin.simpleweb.http.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UnitDaoService {

    @Autowired
    private UnitDao unitDao;


    @Transactional
    public List<Unit> getAll() {
        return unitDao.getAll();
    }


    @Transactional
    public Unit getById(int id) {
        return unitDao.getById(id);
    }


    @Transactional
    public void save(Unit entity) {
        unitDao.save(entity);
    }


    @Transactional
    public void delete(int id) {
        unitDao.delete(id);
    }
}
