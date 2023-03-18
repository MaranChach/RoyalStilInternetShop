package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value = "/admin", produces = "text/html; charset=UTF-8")
public class CRMController {


    @Autowired
    private HttpSession session;

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

    @RequestMapping(value = "/")
    public String mainView(Model model) throws UnsupportedEncodingException {

        System.out.println(session.getId());

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

        return "admin-main-page";
    }



    @RequestMapping("/units")
    public String unitsView(Model model){
        model.addAttribute("units", unitDao.getAll());

        return "admin-units-page";
    }

    @RequestMapping("/unit")
    public String unitView(Model model){
        model.addAttribute("unit", new Unit());

        return "admin-unit-view";
    }

    @RequestMapping("/updateUnit")
    public String unitViewById(@RequestParam("unitId") int id, Model model){
        model.addAttribute("unit", unitDao.getById(id));

        return "admin-unit-view";
    }

    @RequestMapping(value = "/saveUnit")
    public String saveUnit(@ModelAttribute("unit") Unit unit){
        System.out.println(unit);

        unitDao.save(unit);

        return "redirect:/admin/units";
    }

    @RequestMapping("/deleteUnit")
    public String deleteUnit(@RequestParam("unitId") int id, Model model){
        System.out.println("работай сука");

        unitDao.deleteunit(unitDao.getById(id));

        return "redirect:/admin/units";
    }

    @RequestMapping(value = "/products")
    public String productsView(Model model) throws UnsupportedEncodingException {

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

        return "admin-products-view";
    }

    @RequestMapping(value = "/product")
    public String productView(Model model){
        Product product = new Product();

        model.addAttribute("product", product);

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        return "admin-product-view";
    }

    @RequestMapping("/updateProduct")
    public String productViewById(@RequestParam("productId") int id, Model model){
        model.addAttribute("product", productDao.getById(id));

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        return "admin-product-view";
    }





    @RequestMapping(value = "/saveProduct")
    public String saveProduct(@ModelAttribute("newProduct") Product product,
                              @RequestParam("unit") int unitId,
                              @RequestParam("category") int categoryId,
                              @RequestParam("manufacturer") int manufacturerId) {

        product.setUnit(unitDao.getById(unitId));
        product.setCategory(categoryDao.getById(categoryId));
        product.setManufacturer(manufacturerDao.getById(manufacturerId));

        System.out.println(product);

        productDao.save(product);

        return "redirect:/admin/products";
    }



    @RequestMapping("/product/{id}")
    public String productViewById(Model model, @PathVariable int id){
        model.addAttribute("product", productDao.getById(id));

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        return "admin-product-view";
    }



    @RequestMapping("/orders")
    private String ordersView(Model model){
        model.addAttribute("orders", orderDao.getAll());

        return "admin-orders-view";
    }

    @RequestMapping("/order")
    private String orderView(@RequestParam("orderId") int id, Model model){
        model.addAttribute("order", orderDao.getById(id));
        model.addAttribute("iterator", 0);

        return "admin-order-view";
    }


    @RequestMapping(value = "/saveManufacturer")
    public String saveManufacturer(@ModelAttribute("newManufacturer") Manufacturer manufacturer){
        System.out.println(manufacturer);

        manufacturerDao.save(manufacturer);

        return "redirect:/main/admin";
    }

    @RequestMapping(value = "/saveCategory")
    public String saveCategory(@ModelAttribute("newCategory") Category category){
        System.out.println(category);

        categoryDao.save(category);

        return "redirect:/test/admin";
    }
}
