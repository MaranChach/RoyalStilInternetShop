package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.CategoryDao;
import com.trantin.simpleweb.http.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("categories", categoryDao.getAll());

        return "shop-main-page";
    }

    @RequestMapping("/catalog")
    public String getCatalogPage(Model model){
        model.addAttribute("categories", categoryDao.getAll());

        return "shop-catalog-page";
    }

    @RequestMapping("/category")
    public String getCategoryPage(@RequestParam int categoryId , Model model){
        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("curCategory", categoryDao.getById(categoryId));

        model.addAttribute("products",
                productDao.getByCategoryId(categoryId));

        return "shop-category-page";
    }

    @RequestMapping("/product")
    public String getProductPage(@RequestParam int productId, Model model){
        model.addAttribute("product", productDao.getById(productId));

        return "shop-product-page";
    }

    @RequestMapping("/personal-page")
    public String getPersonalPage(Model model){
        return "shop-personal-page";
    }




    @RequestMapping("/categoryTest")
    public String getCategoryPage(Model model){
        model.addAttribute("products", productDao.getAll());

        return "shop-category-page";
    }
    @RequestMapping("/productTest")
    public String getTestProductPage(){
        return "shop-product-page";
    }
}
