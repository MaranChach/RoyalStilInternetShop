package com.trantin.simpleweb.http.dao;


import com.trantin.simpleweb.http.entity.*;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductDao {
    @Autowired
    private SessionFactory sessionFactory;



    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Product", Product.class).getResultList();
    }

    public List<Product> getAllSomeFields() {
        return sessionFactory.getCurrentSession().createQuery("select name, number from Product", Product.class).getResultList();
    }

    public List<Product> getLastThree() {
        return sessionFactory.getCurrentSession().createQuery("from Product", Product.class).setMaxResults(3).getResultList();
    }


    public Map<String, Integer> getMap() {

        List<Product> products =
                sessionFactory.getCurrentSession().createQuery("from Product", Product.class).getResultList();

        Map<String, Integer> productMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            productMap.put(products.get(i).getName(), products.get(i).getId());
        }

        return productMap;
    }


    public List<Product> getSortedByCost(int categoryId, boolean isAsc) {
        String str = "ASC";
        if (!isAsc)
            str = "DESC";

        return sessionFactory.getCurrentSession().createQuery("FROM Product WHERE category.id = " + categoryId + " ORDER BY cost " + str, Product.class).getResultList();
    }

    public List<Product> searchSortedByCost(String searchText, boolean isAsc) {
        String str = "ASC";
        if (!isAsc)
            str = "DESC";

        return sessionFactory.getCurrentSession().createQuery("FROM Product WHERE lower(name) LIKE lower('%" + searchText + "%')" +
                "OR lower(article) LIKE lower('%" + searchText + "%') ORDER BY cost " + str, Product.class).getResultList();
    }

    public List<Product> getByFilters(String searchText, int categoryId, int manufacturerId, String sortType){
        String searchStr = "";
        String cateroryFilterStr = "";
        String manufacturerFilterStr = "";
        String sortStr = "";

        String whereStr = "";
        boolean manyParams = false;

        if(!searchText.isEmpty()){
            whereStr = " WHERE ";
            manyParams = true;
            searchStr = " (lower(name) LIKE lower('%" + searchText + "%')" +
                    " OR lower(article) LIKE lower('%" + searchText + "%')) ";
        }
        if(categoryId != 0){
            whereStr = " WHERE ";
            cateroryFilterStr = " category.id = " + categoryId + " ";
            if (manyParams){
                cateroryFilterStr = " AND " + cateroryFilterStr;
            }
            manyParams = true;
        }
        if(manufacturerId != -1){
            whereStr = " WHERE ";
            manufacturerFilterStr = " manufacturer.id = " + manufacturerId + " ";
            if (manyParams){
                manufacturerFilterStr = " AND " + manufacturerFilterStr;
            }
            manyParams = true;
        }
        if(!sortType.equals("none")){
            if(sortType.equals("costAsc")){
                sortStr = " ORDER BY cost ASC ";
            }
            else if(sortType.equals("costDesc")){
                sortStr = " ORDER BY cost DESC ";
            }
        }

        Query query = sessionFactory.getCurrentSession().createQuery("FROM Product " + whereStr + cateroryFilterStr + searchStr + manufacturerFilterStr + sortStr, Product.class);

        System.out.println("FROM Product " + whereStr + cateroryFilterStr + searchStr + manufacturerFilterStr + sortStr);

        return query.getResultList();
    }


    public Product getById(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Product where id = " + id, Product.class).getSingleResult();
    }


    public List<Product> getByManufacturer(Manufacturer manufacturer){
        return sessionFactory.getCurrentSession().createQuery("from Product where manufacturer = " + manufacturer, Product.class).getResultList();
    }


    public List<Product> getByCategory(Category category){
        return sessionFactory.getCurrentSession().createQuery("from Product where category = " + category, Product.class).getResultList();
    }

    public List<Product> getByCategoryId(int categoryId){
        return sessionFactory.getCurrentSession().createQuery("from Product where category.id = " + categoryId, Product.class).getResultList();
    }


    public List<Product> getByDetail(DetailsParameter parameter, double value){
        List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product", Product.class).getResultList();

        List<Product> result = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            List<DetailsAttribute> attributes = product.getDetails().getAttributes();

            for (int j = 0; j < attributes.size(); j++) {
                DetailsAttribute curAttr = attributes.get(j);

                if (curAttr.getParameter().equals(parameter.getName()) && (curAttr.getValue() == value))
                    result.add(product);
            }
        }

        return result;
    }


    public List<Product> search(String searchText){

        return sessionFactory.getCurrentSession().createQuery("FROM Product WHERE lower(name) LIKE lower('%" + searchText + "%') " +
                "OR lower(article) LIKE lower('%" + searchText + "%') ", Product.class).getResultList();

    }

    public List<Product> searchByName(String searchText){

        return sessionFactory.getCurrentSession().createQuery("from Product where lower(name) like lower('%" + searchText + "%')", Product.class).getResultList();

    }

    public void save(Product entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void update(Product entity) {
        sessionFactory.getCurrentSession().refresh(entity);
    }

    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    public void deleteById(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Product where id = " + id).executeUpdate();
    }
}
