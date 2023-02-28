package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.Employee;
import com.trantin.simpleweb.http.exceptions.EmployeeIncorrectData;
import com.trantin.simpleweb.http.exceptions.NoSuchEmployeeException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDaoImplement implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;



    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from employee", Employee.class).getResultList();
    }


    public void addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }


    public Employee getDueId(int id) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, id);

        if (employee==null){
            throw new NoSuchEmployeeException("No employee with id " + id);
        }

        return employee;
    }

    public List<Employee> getDueName(String name) {
        return sessionFactory.getCurrentSession().createQuery("select from employee where name = " + name).getResultList();
    }


    public void deleteEmployee(int id) {

        sessionFactory.getCurrentSession().createQuery("delete from employee where id = " + id).executeUpdate();
        //sessionFactory.getCurrentSession().delete(getDueId(id));
    }
}
