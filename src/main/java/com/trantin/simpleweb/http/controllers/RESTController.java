package com.trantin.simpleweb.http.controllers;

import com.trantin.simpleweb.http.dao.ImageDao;
import com.trantin.simpleweb.http.dao.OrderDao;
import com.trantin.simpleweb.http.dao.ProductDao;
import com.trantin.simpleweb.http.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ImageDao imageDao;


    @GetMapping("/products")
    public List<Product> getProducts(){
        return productDao.getAllSomeFields();
    }

    @GetMapping("/weeklyOrders")
    public List<Object[]> getWeeklyOrders(){
        return orderDao.getByLastWeek();
    }

    @GetMapping("/mainPageImagesNumber")
    public int getMainPageImagesNumber(){
        return imageDao.getAll().size();
    }

}
