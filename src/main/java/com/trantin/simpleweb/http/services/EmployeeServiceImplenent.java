package com.trantin.simpleweb.http.services;

import com.trantin.simpleweb.http.dao.EmployeeDao;
import com.trantin.simpleweb.http.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImplenent implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;


    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }



    @Transactional
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }


    @Transactional
    public Employee getDueId(int id) {
        return employeeDao.getDueId(id);
    }

    @Transactional
    public List<Employee> getDueName(String name){
        return employeeDao.getDueName(name);
    }

    @Transactional
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }
}
