package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
public class MainController {

    //region DAO
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UnitDao unitDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ManufacturerDao manufacturerDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DetailsDao detailsDao;

    //endregion

    @RequestMapping(value = "/main")
    public String mainView(Model model) throws UnsupportedEncodingException {

        model.addAttribute("newProduct", new Product());
        model.addAttribute("newUnit", new Unit());
        model.addAttribute("newManufacturer", new Manufacturer());
        model.addAttribute("newClient", new Client());
        model.addAttribute("newDetails", new Details());
        model.addAttribute("newOrder", new Order());
        model.addAttribute("newCategory", new Category());

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("products", productDao.getAll());
        model.addAttribute("units", unitDao.getAll());
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("manufacturers", manufacturerDao.getAll());
        model.addAttribute("orders", orderDao.getAll());
        model.addAttribute("clients", clientDao.getAll());
        model.addAttribute("details", detailsDao.getAll());

        return "main-page";
    }


    @RequestMapping(value = "/product")
    public String saveProduct(Model model){
        Product product = new Product();

        model.addAttribute("product", product);

        return "product-view";
    }

    @RequestMapping(value = "/saveProduct")
    public String saveProductOld(@ModelAttribute("newProduct") Product product,
                              @RequestParam("unit") int unitId,
                              @RequestParam("category") int categoryId,
                              @RequestParam("manufacturer") int manufacturerId) {

        product.setUnit(unitDao.getById(unitId));
        product.setCategory(categoryDao.getById(categoryId));
        product.setManufacturer(manufacturerDao.getById(manufacturerId));

        //product.setName(new String(product.getName().getBytes("ISO-8859-1"), "UTF-8"));

        System.out.println(product);

        productDao.save(product);

        return "redirect:/test";
    }

    @RequestMapping(value = "/saveUnit")
    public String saveUnit(@ModelAttribute("newUnit") Unit unit){
        System.out.println(unit);

        unitDao.save(unit);

        return "redirect:/test";
    }

    @RequestMapping(value = "/saveManufacturer")
    public String saveManufacturer(@ModelAttribute("newManufacturer") Manufacturer manufacturer){
        System.out.println(manufacturer);

        manufacturerDao.save(manufacturer);

        return "redirect:/test";
    }

    @RequestMapping(value = "/saveCategory")
    public String saveCategory(@ModelAttribute("newCategory") Category category){
        System.out.println(category);

        categoryDao .save(category);

        return "redirect:/test";
    }
}
