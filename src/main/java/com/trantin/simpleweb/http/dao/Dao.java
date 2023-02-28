package com.trantin.simpleweb.http.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    T getById(int id);

    void save(T entity);

    void delete(int id);
}
