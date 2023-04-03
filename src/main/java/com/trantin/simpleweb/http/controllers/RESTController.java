package com.trantin.simpleweb.http.controllers;

import com.trantin.simpleweb.http.dao.OrderDao;
import com.trantin.simpleweb.http.dao.ProductDao;
import com.trantin.simpleweb.http.entity.Employee;
import com.trantin.simpleweb.http.entity.Order;
import com.trantin.simpleweb.http.entity.Product;
import com.trantin.simpleweb.http.exceptions.NoSuchEmployeeException;
import com.trantin.simpleweb.http.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;


    @GetMapping("/products")
    public List<Product> getProducts(){
        return productDao.getAllSomeFields();
    }

    @GetMapping("/weeklyOrders")
    public List<Object[]> getWeeklyOrders(){
        return orderDao.getByLastWeek();
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmlpoyees(){
        List<Employee> list = employeeService.getAllEmployees();
        return list;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeForId(@PathVariable int id){

        return employeeService.getDueId(id);
    }

    @GetMapping("/employeesa/{name}")
    public List<Employee> getEmployeeForId(@PathVariable String name){

        return employeeService.getDueName(name);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){

        employeeService.addEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.addEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){

        if(employeeService.getDueId(id) == null){
            throw new NoSuchEmployeeException("No employee with id = " + id);
        }

        employeeService.deleteEmployee(id);


        return "Deleted emp with id = " + id;
    }

}
