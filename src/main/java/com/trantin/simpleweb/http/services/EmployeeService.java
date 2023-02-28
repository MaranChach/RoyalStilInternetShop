package com.trantin.simpleweb.http.services;

import com.trantin.simpleweb.http.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public void addEmployee(Employee employee);

    public Employee getDueId(int id);

    public List<Employee> getDueName(String name);

    public void deleteEmployee(int id);
}
